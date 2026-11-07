# SpectreBound Armor 3D (NeoForge 1.21.1)

Chuyen tu goi Nexo/MythicArmors "Spectre Bound Armor v1.1" sang mod NeoForge 1.21.1.

| Item | ID | Cach hien thi |
|------|----|---------------|
| Mu | `spectrebound:spectrebound_helmet` | Item model 3D (`models/item/spectrebound_helmet.json`), ve len dau qua CustomHeadLayer |
| Giap nguc | `spectrebound:spectrebound_chestplate` | Model 3D tu code (`SpectreArmorModel`) |
| Quan | `spectrebound:spectrebound_leggings` | Model 3D tu code |
| Giay | `spectrebound:spectrebound_boots` | Model 3D tu code |
| Dao | `spectrebound:spectrebound_dagger` | Item model 3D |
| Plushie | `spectrebound:spectrebound_plushie` | Block (xoay theo huong dat) |

Mod chua co recipe: lay bang creative tab "SpectreBound Armor" hoac `/give @s spectrebound:spectrebound_chestplate`.

## Build bang GitHub
```
git init
git add .
git commit -m "SpectreBound armor"
git branch -M main
git remote add origin https://github.com/<ten-ban>/spectrebound.git
git push -u origin main
```
Vao tab **Actions** > workflow **build** > tai artifact `spectrebound-jar` (file `spectrebound-1.1.0.jar`).
Muon tao Release: `git tag v1.1.0 && git push origin v1.1.0`.

Cai jar tren **ca server va client** (client can de thay model).

## Chinh thong so
- Chi so giap: `ModArmorMaterials.java` (mac dinh nhu netherite) va `helmetProps()` trong `ModItems.java` cho mu.
- Dao: `SwordItem.createAttributes(Tiers.NETHERITE, 2, -1.8F)` trong `ModItems.java`.

## Model giap duoc sinh nhu the nao
`tools/convert.py` doc `hydra_spectrebound_armor.bbmodel` va sinh `SpectreArmorModel.java`
(78 cube; 23 cube "inverted" bi bo vi giap ve bang render type no-cull nen khong can mat sau).
Chay lai khi sua model trong Blockbench:
```
python3 tools/convert.py hydra_spectrebound_armor.bbmodel out/
```
Texture giap la `textures/models/armor/spectrebound_layer_1.png` va `_layer_2.png` (cung 1 anh 128x128, lay tu texture 0 cua bbmodel).
