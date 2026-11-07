import json, math, base64, re, os, sys

SRC = sys.argv[1] if len(sys.argv) > 1 else 'MythicArmors/models/hydra_spectrebound_armor.bbmodel'
OUT = sys.argv[2] if len(sys.argv) > 2 else 'out'
d = json.load(open(SRC))
els = {e['uuid']: e for e in d['elements']}
grp = {g['uuid']: g for g in d['groups']}

# vanilla pivots in Blockbench coordinates
PIVOT = {
    'body': (0, 24, 0),
    'right_arm': (-5, 22, 0),
    'left_arm': (5, 22, 0),
    'right_leg': (-1.9, 12, 0),
    'left_leg': (1.9, 12, 0),
}

def fmt(v):
    v = round(v, 5)
    if v == 0: v = 0.0
    s = ('%.5f' % v).rstrip('0').rstrip('.')
    if s in ('', '-0'): s = '0'
    return s + 'F'

def norm_cube(c):
    """return (from,to,inverted_flag) with from<=to"""
    f, t = list(c['from']), list(c['to'])
    inv = any(t[i] < f[i] for i in range(3))
    if inv:
        for i in range(3):
            if t[i] < f[i]:
                f[i], t[i] = t[i], f[i]
    return f, t, inv

def cube_key(c):
    f, t, _ = norm_cube(c)
    return (tuple(round(x, 4) for x in f), tuple(round(x, 4) for x in t), tuple(c.get('uv_offset', [0, 0])),
            tuple(c.get('rotation', [0, 0, 0])), bool(c.get('mirror_uv')), round(c.get('inflate', 0), 4))

stats = {'cubes': 0, 'skipped_inverted': 0}

class Part:
    def __init__(self, name):
        self.name = name
        self.offset = (0, 0, 0)
        self.rot = (0, 0, 0)
        self.cubes = []     # (u, v, mirror, x, y, z, w, h, d, inflate)
        self.children = []

def build_group(node, parent_origin, used_names):
    """node: outliner dict; returns Part"""
    g = grp[node['uuid']]
    name = re.sub(r'[^a-z0-9_]', '_', g['name'].lower())
    base, i = name, 2
    while name in used_names:
        name = f'{base}_{i}'; i += 1
    used_names.add(name)
    p = Part(name)
    o = g['origin']
    p.offset = (o[0] - parent_origin[0], parent_origin[1] - o[1], o[2] - parent_origin[2])
    r = g.get('rotation', [0, 0, 0])
    p.rot = (-math.radians(r[0]), math.radians(r[1]), -math.radians(r[2]))
    # collect cubes first to detect duplicate inverted ones
    cube_children = [els[c] for c in node['children'] if isinstance(c, str)]
    keys = {cube_key(c) for c in cube_children if not norm_cube(c)[2]}
    for c in cube_children:
        f, t, inv = norm_cube(c)
        if inv and cube_key(c) in keys:
            stats['skipped_inverted'] += 1
            continue  # armor render type is no-cull, mirrored duplicate not needed
        stats['cubes'] += 1
        add_cube(p, o, c, f, t, used_names)
    for c in node['children']:
        if not isinstance(c, str):
            p.children.append(build_group(c, o, used_names))
    return p

def add_cube(part, group_origin, c, f, t, used_names):
    u, v = c.get('uv_offset', [0, 0])
    mirror = bool(c.get('mirror_uv'))
    infl = c.get('inflate', 0) or 0
    rot = c.get('rotation', [0, 0, 0])
    if any(abs(x) > 1e-9 for x in rot):
        co = c.get('origin', [0, 0, 0])
        sub = Part('cube_%d' % (len(used_names) + 1))
        used_names.add(sub.name)
        sub.offset = (co[0] - group_origin[0], group_origin[1] - co[1], co[2] - group_origin[2])
        sub.rot = (-math.radians(rot[0]), math.radians(rot[1]), -math.radians(rot[2]))
        sub.cubes.append((u, v, mirror, f[0] - co[0], co[1] - t[1], f[2] - co[2],
                          t[0] - f[0], t[1] - f[1], t[2] - f[2], infl))
        part.children.append(sub)
    else:
        part.cubes.append((u, v, mirror, f[0] - group_origin[0], group_origin[1] - t[1], f[2] - group_origin[2],
                           t[0] - f[0], t[1] - f[1], t[2] - f[2], infl))

# ---- decide target slot/part for each top-level group's children
SLOTS = {'CHEST': {}, 'LEGS': {}, 'FEET': {}}   # slot -> part_name -> [Part]

def target_part(group, child_name):
    o = group['origin']
    if group['name'] == 'body':
        return 'body'
    side = 'right' if o[0] < 0 else 'left'
    if 'arm' in group['name']:
        return f'{side}_arm'
    return f'{side}_leg'

def slot_of(child_name):
    if child_name.startswith('chestplate'): return 'CHEST'
    if child_name.startswith('legging'): return 'LEGS'
    if child_name.startswith('boot'): return 'FEET'
    raise ValueError(child_name)

for top in d['outliner']:
    tg = grp[top['uuid']]
    tp = target_part(tg, None)
    for ch in top['children']:
        if isinstance(ch, str):
            raise ValueError('loose cube under top group')
        cg = grp[ch['uuid']]
        slot = slot_of(cg['name'])
        used = set()
        part = build_group(ch, PIVOT[tp], used)
        SLOTS[slot].setdefault(tp, []).append(part)

# ---- emit java
def emit_part(p, var, parent_var, indent):
    pad = ' ' * indent
    out = []
    cb = 'CubeListBuilder.create()'
    for (u, v, m, x, y, z, w, h, dd, infl) in p.cubes:
        cb += f'\n{pad}        .texOffs({int(u)}, {int(v)})' + ('.mirror()' if m else '') + \
              f'.addBox({fmt(x)}, {fmt(y)}, {fmt(z)}, {fmt(w)}, {fmt(h)}, {fmt(dd)}, ' + \
              (f'new CubeDeformation({fmt(infl)})' if infl else 'CubeDeformation.NONE') + ')' + ('.mirror(false)' if m else '')
    ox, oy, oz = p.offset
    rx, ry, rz = p.rot
    if abs(rx) + abs(ry) + abs(rz) > 1e-9:
        pose = f'PartPose.offsetAndRotation({fmt(ox)}, {fmt(oy)}, {fmt(oz)}, {fmt(rx)}, {fmt(ry)}, {fmt(rz)})'
    else:
        pose = f'PartPose.offset({fmt(ox)}, {fmt(oy)}, {fmt(oz)})'
    has_children = bool(p.children)
    target = f'{var} = ' if has_children else ''
    out.append(f'{pad}{"PartDefinition " if has_children else ""}{target}{parent_var}.addOrReplaceChild("{p.name}", {cb}, {pose});')
    for i, c in enumerate(p.children):
        out += emit_part(c, f'{var}_{i}', var, indent)
    return out

lines = []
for slot, parts in SLOTS.items():
    lines.append(f'    private static LayerDefinition create{slot.capitalize()}() {{')
    lines.append('        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);')
    lines.append('        PartDefinition root = mesh.getRoot();')
    lines.append('        // Giu day du 7 part cua HumanoidModel, xoa cube vanilla de khong ve da/ao')
    lines.append('        clearVanilla(root);')
    for part_name, plist in parts.items():
        var = part_name
        lines.append(f'        PartDefinition {var} = root.getChild("{part_name}");')
        for i, p in enumerate(plist):
            lines += emit_part(p, f'{var}_p{i}', var, 8)
    lines.append('        return LayerDefinition.create(mesh, 128, 128);')
    lines.append('    }')
    lines.append('')
body = '\n'.join(lines)

java = f'''package com.llamastudio.spectrebound.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * File nay duoc SINH TU DONG tu hydra_spectrebound_armor.bbmodel (xem tools/convert.py).
 * Moi mon giap (CHEST / LEGS / FEET) la mot LayerDefinition rieng de khong ve trung lap
 * khi vanilla bat part "body" cho ca nguc va quan.
 */
public class SpectreArmorModel extends HumanoidModel<LivingEntity> {{

    public static final ModelLayerLocation CHEST_LAYER = layer("spectre_chest");
    public static final ModelLayerLocation LEGS_LAYER = layer("spectre_legs");
    public static final ModelLayerLocation FEET_LAYER = layer("spectre_feet");

    private static ModelLayerLocation layer(String name) {{
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("spectrebound", name), "main");
    }}

    public SpectreArmorModel(ModelPart root) {{
        super(root);
    }}

    public static LayerDefinition chestLayer() {{ return createChest(); }}
    public static LayerDefinition legsLayer() {{ return createLegs(); }}
    public static LayerDefinition feetLayer() {{ return createFeet(); }}

    private static void clearVanilla(PartDefinition root) {{
        // Thay moi part vanilla bang part rong cung pivot (tranh "No such child" trong HumanoidModel)
        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
    }}

{body}}}
'''
os.makedirs('' + OUT + '', exist_ok=True)
open('' + OUT + '/SpectreArmorModel.java', 'w').write(java)

# texture 0
t0 = d['textures'][0]
src = t0['source']
b64 = src.split(',', 1)[1]
open('' + OUT + '/armor_tex.png', 'wb').write(base64.b64decode(b64))
print(stats, {s: {k: len(v) for k, v in p.items()} for s, p in SLOTS.items()})
print('java lines', java.count('\n'))
