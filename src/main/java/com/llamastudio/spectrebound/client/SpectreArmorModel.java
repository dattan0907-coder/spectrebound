package com.llamastudio.spectrebound.client;

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
public class SpectreArmorModel extends HumanoidModel<LivingEntity> {

    public static final ModelLayerLocation CHEST_LAYER = layer("spectre_chest");
    public static final ModelLayerLocation LEGS_LAYER = layer("spectre_legs");
    public static final ModelLayerLocation FEET_LAYER = layer("spectre_feet");

    private static ModelLayerLocation layer(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("spectrebound", name), "main");
    }

    public SpectreArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition chestLayer() { return createChest(); }
    public static LayerDefinition legsLayer() { return createLegs(); }
    public static LayerDefinition feetLayer() { return createFeet(); }

    private static void clearVanilla(PartDefinition root) {
        // Thay moi part vanilla bang part rong cung pivot (tranh "No such child" trong HumanoidModel)
        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));
    }

    private static LayerDefinition createChest() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        // Giu day du 7 part cua HumanoidModel, xoa cube vanilla de khong ve da/ao
        clearVanilla(root);
        PartDefinition body = root.getChild("body");
        PartDefinition body_p0 = body.addOrReplaceChild("chestplate_body", CubeListBuilder.create()
                .texOffs(33, 81).addBox(-4F, 0.825F, -2F, 8F, 12F, 4F, new CubeDeformation(0.2575F))
                .texOffs(64, 79).addBox(-4.5F, 0.525F, -2.5F, 9F, 8F, 5F, CubeDeformation.NONE), PartPose.offset(0F, -1.75F, 0F));
        PartDefinition body_p0_0 = body_p0.addOrReplaceChild("chestplate_body_plackart_front", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 2.2F, -7F, 0.5236F, 0F, 0F));
        body_p0_0.addOrReplaceChild("cube_3", CubeListBuilder.create()
                .texOffs(105, 21).addBox(-1.90463F, 0.03753F, -2.09537F, 4F, 0F, 4F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, 3.25F, 0F, 0.7854F, 0F));
        body_p0_0.addOrReplaceChild("chestplate_body_plackart_front_right2", CubeListBuilder.create()
                .texOffs(111, 33).addBox(-0.057F, 5.79253F, -0.12224F, 3F, 3F, 1F, CubeDeformation.NONE)
                .texOffs(100, 109).addBox(-0.057F, 1.79253F, -0.12224F, 5F, 4F, 1F, CubeDeformation.NONE)
                .texOffs(105, 0).addBox(-0.057F, 0.79253F, -0.12224F, 5F, 1F, 4F, CubeDeformation.NONE)
                .texOffs(111, 26).addBox(3.943F, 1.79253F, 0.87776F, 1F, 3F, 3F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.43633F, 0F));
        body_p0_0.addOrReplaceChild("chestplate_body_plackart_front_left2", CubeListBuilder.create()
                .texOffs(111, 33).mirror().addBox(-2.943F, 5.79253F, -0.12224F, 3F, 3F, 1F, CubeDeformation.NONE).mirror(false)
                .texOffs(100, 109).mirror().addBox(-4.943F, 1.79253F, -0.12224F, 5F, 4F, 1F, CubeDeformation.NONE).mirror(false)
                .texOffs(105, 0).mirror().addBox(-4.943F, 0.79253F, -0.12224F, 5F, 1F, 4F, CubeDeformation.NONE).mirror(false)
                .texOffs(111, 26).mirror().addBox(-4.943F, 1.79253F, 0.87776F, 1F, 3F, 3F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.43633F, 0F));
        PartDefinition body_p0_1 = body_p0.addOrReplaceChild("chestplate_body_parts", CubeListBuilder.create(), PartPose.offset(6F, 1.79289F, 3.3F));
        PartDefinition body_p0_1_0 = body_p0_1.addOrReplaceChild("bone12", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.8F, -0.3F, -7.1F, -0.1309F, 0F, 0F));
        body_p0_1_0.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(110, 84).addBox(-1.14142F, -2.14142F, 0F, 3F, 3F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.2F, 1.45711F, -0.5F, 0F, 0F, -0.7854F));
        body_p0_1_0.addOrReplaceChild("cube_9", CubeListBuilder.create()
                .texOffs(113, 109).addBox(1.85858F, -0.14142F, 0F, 1F, 1F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.2F, 1.45711F, -0.5F, 0F, 0F, -0.7854F));
        PartDefinition body_p0_1_1 = body_p0_1.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-11.2F, -0.3F, -7.1F, -0.1309F, 0F, 0F));
        body_p0_1_1.addOrReplaceChild("cube_11", CubeListBuilder.create()
                .texOffs(110, 84).mirror().addBox(-1.85858F, -2.14142F, 0F, 3F, 3F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-0.2F, 1.45711F, -0.5F, 0F, 0F, 0.7854F));
        body_p0_1_1.addOrReplaceChild("cube_12", CubeListBuilder.create()
                .texOffs(113, 109).mirror().addBox(-2.85858F, -0.14142F, 0F, 1F, 1F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-0.2F, 1.45711F, -0.5F, 0F, 0F, 0.7854F));
        PartDefinition body_p0_1_2 = body_p0_1.addOrReplaceChild("clock", CubeListBuilder.create(), PartPose.offset(-12F, 0F, 0F));
        PartDefinition body_p0_1_2_0 = body_p0_1_2.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offset(0F, 0F, 0F));
        body_p0_1_2_0.addOrReplaceChild("cube_15", CubeListBuilder.create()
                .texOffs(41, 54).addBox(-1.5F, -3F, -1F, 9F, 24F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(3F, 1.65711F, 0.4F, 0.30543F, 0F, 0F));
        PartDefinition body_p0_1_2_0_1 = body_p0_1_2_0.addOrReplaceChild("group8", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 0F, 0F, 0.21817F, 0F, 0F));
        body_p0_1_2_0_1.addOrReplaceChild("cube_17", CubeListBuilder.create()
                .texOffs(106, 66).addBox(-1.20106F, -3.61528F, -0.41422F, 4F, 4F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 1.45711F, 0F, 0F, 0F, 0.7854F));
        body_p0_1_2_0_1.addOrReplaceChild("cube_18", CubeListBuilder.create()
                .texOffs(106, 66).mirror().addBox(-2.79894F, -3.61528F, -0.41422F, 4F, 4F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(12F, 1.45711F, 0F, 0F, 0F, -0.7854F));
        PartDefinition body_p0_1_2_0_1_2 = body_p0_1_2_0_1.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offset(6F, 10.12552F, 0.63507F));
        body_p0_1_2_0_1_2.addOrReplaceChild("cube_20", CubeListBuilder.create()
                .texOffs(64, 54).addBox(0.00378F, -1.82924F, -2.6852F, 9F, 21F, 3F, CubeDeformation.NONE), PartPose.offsetAndRotation(-6.4F, -8.93824F, 0.1852F, 0F, 0F, 0.34907F));
        body_p0_1_2_0_1_2.addOrReplaceChild("cube_21", CubeListBuilder.create()
                .texOffs(64, 54).mirror().addBox(-9.00378F, -1.82924F, -2.6852F, 9F, 21F, 3F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(6.4F, -8.93824F, 0.1852F, 0F, 0F, -0.34907F));
        PartDefinition right_arm = root.getChild("right_arm");
        PartDefinition right_arm_p0 = right_arm.addOrReplaceChild("chestplate_rightarm", CubeListBuilder.create()
                .texOffs(93, 73).mirror().addBox(-3F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.255F)).mirror(false)
                .texOffs(83, 93).mirror().addBox(-3F, -1.5F, -2F, 4F, 12F, 4F, new CubeDeformation(0.26F)).mirror(false), PartPose.offset(0F, 0F, 0F));
        right_arm_p0.addOrReplaceChild("cube_2", CubeListBuilder.create()
                .texOffs(38, 98).mirror().addBox(-3F, -2F, -2.5F, 4F, 3F, 5F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-1.91596F, 6.57939F, 0F, 0F, 0F, -1.74533F));
        right_arm_p0.addOrReplaceChild("cube_3", CubeListBuilder.create()
                .texOffs(105, 13).mirror().addBox(1F, -2F, -2.5F, 2F, 2F, 5F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-1.91596F, 6.57939F, 0F, 0F, 0F, -1.74533F));
        PartDefinition right_arm_p0_2 = right_arm_p0.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.55341F, -1.50835F, 0F, 0F, 0F, 0.04363F));
        right_arm_p0_2.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(58, 93).mirror().addBox(-1F, -2.5F, -3.5F, 5F, 3F, 7F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(1.53143F, 1.57395F, 0F, 0F, 0F, -0.17453F));
        right_arm_p0_2.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(94, 44).mirror().addBox(-1F, -2.5F, -3F, 5F, 2F, 6F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(2.53143F, 4.47395F, 0F, 0F, 0F, -0.74176F));
        right_arm_p0_2.addOrReplaceChild("cube_7", CubeListBuilder.create()
                .texOffs(33, 79).mirror().addBox(-3F, -1.5F, -3.5F, 2F, 1F, 7F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(1.53143F, 1.57395F, 0F, 0F, 0F, -0.17453F));
        right_arm_p0_2.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(100, 90).mirror().addBox(-4F, -2.5F, -3.5F, 3F, 1F, 7F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(1.53143F, 1.57395F, 0F, 0F, 0F, -0.17453F));
        PartDefinition right_arm_p0_3 = right_arm_p0.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.09116F, 7.50591F, 0F, 0F, 0F, -1.65806F));
        right_arm_p0_3.addOrReplaceChild("cube_10", CubeListBuilder.create()
                .texOffs(110, 73).mirror().addBox(-1.5F, -0.5F, -1.5F, 3F, 1F, 3F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.7854F, 0F));
        right_arm_p0_3.addOrReplaceChild("cube_11", CubeListBuilder.create()
                .texOffs(58, 87).mirror().addBox(1.5F, -0.5F, -1.5F, 1F, 1F, 1F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.7854F, 0F));
        right_arm_p0_3.addOrReplaceChild("cube_12", CubeListBuilder.create()
                .texOffs(58, 90).mirror().addBox(-1.5F, -0.5F, 1.5F, 1F, 1F, 1F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.7854F, 0F));
        PartDefinition left_arm = root.getChild("left_arm");
        PartDefinition left_arm_p0 = left_arm.addOrReplaceChild("chestplate_leftarm", CubeListBuilder.create()
                .texOffs(93, 73).addBox(-1F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.255F))
                .texOffs(83, 93).addBox(-1F, -1.5F, -2F, 4F, 12F, 4F, new CubeDeformation(0.26F)), PartPose.offset(0F, 0F, 0F));
        left_arm_p0.addOrReplaceChild("cube_2", CubeListBuilder.create()
                .texOffs(38, 98).addBox(-1F, -2F, -2.5F, 4F, 3F, 5F, CubeDeformation.NONE), PartPose.offsetAndRotation(1.91596F, 6.57939F, 0F, 0F, 0F, 1.74533F));
        left_arm_p0.addOrReplaceChild("cube_3", CubeListBuilder.create()
                .texOffs(105, 13).addBox(-3F, -2F, -2.5F, 2F, 2F, 5F, CubeDeformation.NONE), PartPose.offsetAndRotation(1.91596F, 6.57939F, 0F, 0F, 0F, 1.74533F));
        PartDefinition left_arm_p0_2 = left_arm_p0.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offsetAndRotation(4.65341F, -1.50835F, 0F, 0F, 0F, -0.04363F));
        left_arm_p0_2.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(58, 93).addBox(-4F, -2.5F, -3.5F, 5F, 3F, 7F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.63143F, 1.57395F, 0F, 0F, 0F, 0.17453F));
        left_arm_p0_2.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(94, 44).addBox(-4F, -2.5F, -3F, 5F, 2F, 6F, CubeDeformation.NONE), PartPose.offsetAndRotation(-2.63143F, 4.47395F, 0F, 0F, 0F, 0.74176F));
        left_arm_p0_2.addOrReplaceChild("cube_7", CubeListBuilder.create()
                .texOffs(33, 79).addBox(1F, -1.5F, -3.5F, 2F, 1F, 7F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.63143F, 1.57395F, 0F, 0F, 0F, 0.17453F));
        left_arm_p0_2.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(100, 90).addBox(1F, -2.5F, -3.5F, 3F, 1F, 7F, CubeDeformation.NONE), PartPose.offsetAndRotation(-1.63143F, 1.57395F, 0F, 0F, 0F, 0.17453F));
        PartDefinition left_arm_p0_3 = left_arm_p0.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offsetAndRotation(4.09116F, 7.50591F, 0F, 0F, 0F, 1.65806F));
        left_arm_p0_3.addOrReplaceChild("cube_10", CubeListBuilder.create()
                .texOffs(110, 73).addBox(-1.5F, -0.5F, -1.5F, 3F, 1F, 3F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.7854F, 0F));
        left_arm_p0_3.addOrReplaceChild("cube_11", CubeListBuilder.create()
                .texOffs(58, 87).addBox(-2.5F, -0.5F, -1.5F, 1F, 1F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.7854F, 0F));
        left_arm_p0_3.addOrReplaceChild("cube_12", CubeListBuilder.create()
                .texOffs(58, 90).addBox(0.5F, -0.5F, 1.5F, 1F, 1F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.7854F, 0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    private static LayerDefinition createLegs() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        // Giu day du 7 part cua HumanoidModel, xoa cube vanilla de khong ve da/ao
        clearVanilla(root);
        PartDefinition body = root.getChild("body");
        PartDefinition body_p0 = body.addOrReplaceChild("legging_body", CubeListBuilder.create(), PartPose.offset(0F, 11.25F, 0F));
        PartDefinition body_p0_0 = body_p0.addOrReplaceChild("legging_body_parts", CubeListBuilder.create(), PartPose.offset(0F, 0F, 0F));
        PartDefinition body_p0_0_0 = body_p0_0.addOrReplaceChild("legging_body_tassets", CubeListBuilder.create(), PartPose.offset(0F, 1F, 0F));
        PartDefinition body_p0_0_0_0 = body_p0_0_0.addOrReplaceChild("legging_body_tasset_front", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, -1.5F, -2.5F, -0.21817F, 0F, 0F));
        body_p0_0_0_0.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(110, 78).addBox(-1.41421F, -1.41421F, -1.12986F, 3F, 3F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, -0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_0.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(33, 75).addBox(-2.41421F, 0.58579F, -1.12986F, 1F, 1F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, -0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_0.addOrReplaceChild("cube_7", CubeListBuilder.create()
                .texOffs(25, 84).addBox(0.58579F, -2.41421F, -1.12986F, 1F, 1F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, -0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_0.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(25, 88).addBox(-0.91421F, -0.91421F, -2.12986F, 2F, 2F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, 0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_0.addOrReplaceChild("cube_9", CubeListBuilder.create()
                .texOffs(17, 107).addBox(-2F, -3F, 0F, 4F, 8F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 3.45F, -1.1F, -0.08727F, 0F, 0F));
        PartDefinition body_p0_0_0_1 = body_p0_0_0.addOrReplaceChild("legging_body_tasset_back", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, -1.5F, 2.5F, 0.21817F, 0F, 0F));
        body_p0_0_0_1.addOrReplaceChild("cube_11", CubeListBuilder.create()
                .texOffs(81, 110).addBox(-1.41421F, -1.41421F, -0.87014F, 3F, 3F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, 0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_1.addOrReplaceChild("cube_12", CubeListBuilder.create()
                .texOffs(25, 92).addBox(-2.41421F, 0.58579F, -0.87014F, 1F, 1F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, 0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_1.addOrReplaceChild("cube_13", CubeListBuilder.create()
                .texOffs(92, 110).addBox(0.58579F, -2.41421F, -0.87014F, 1F, 1F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, 0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_1.addOrReplaceChild("cube_14", CubeListBuilder.create()
                .texOffs(111, 40).addBox(-0.91421F, -0.91421F, 1.12986F, 2F, 2F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0.75F, -0.25F, 0F, 0F, 0.7854F));
        body_p0_0_0_1.addOrReplaceChild("cube_15", CubeListBuilder.create()
                .texOffs(28, 107).addBox(-2F, -3F, -1F, 4F, 8F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 3.45F, 1.1F, 0.08727F, 0F, 0F));
        PartDefinition right_leg = root.getChild("right_leg");
        PartDefinition right_leg_p0 = right_leg.addOrReplaceChild("legging_rightleg", CubeListBuilder.create()
                .texOffs(89, 54).mirror().addBox(-2F, -1.5F, -2F, 4F, 14F, 4F, new CubeDeformation(0.255F)).mirror(false), PartPose.offset(-0.1F, 0F, 0F));
        PartDefinition right_leg_p0_0 = right_leg_p0.addOrReplaceChild("legging_rightleg_parts", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.2618F));
        PartDefinition right_leg_p0_0_0 = right_leg_p0_0.addOrReplaceChild("legging_rightleg_tasset", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.25F, -3F, 0F, 0F, 0F, 0.61087F));
        right_leg_p0_0_0.addOrReplaceChild("cube_4", CubeListBuilder.create()
                .texOffs(17, 98).addBox(0.00865F, -2.13538F, -3F, 4F, 2F, 6F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1F, 2F, 0F, 0F, 0F, -0.1309F));
        right_leg_p0_0_0.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(0, 96).addBox(0.21388F, -3.40979F, -3F, 2F, 7F, 6F, CubeDeformation.NONE), PartPose.offsetAndRotation(3.22073F, 3.95746F, 0F, 0F, 0F, -0.04363F));
        right_leg_p0_0_0.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(94, 29).addBox(-1.78612F, -3.40979F, -3F, 2F, 8F, 6F, CubeDeformation.NONE), PartPose.offsetAndRotation(3.22073F, 3.95746F, 0F, 0F, 0F, -0.04363F));
        PartDefinition right_leg_p0_0_0_3 = right_leg_p0_0_0.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offsetAndRotation(2.72307F, 1.47938F, -1.7F, 0F, 0.08727F, 0.17453F));
        right_leg_p0_0_0_3.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(72, 104).addBox(-1.36914F, -1.50286F, -1F, 2F, 10F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, -0.2F, -0.21817F, 0F, 0.04363F));
        PartDefinition right_leg_p0_0_0_4 = right_leg_p0_0_0.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.72307F, 1.47938F, 1.7F, 0F, -0.08727F, 0.17453F));
        right_leg_p0_0_0_4.addOrReplaceChild("cube_10", CubeListBuilder.create()
                .texOffs(106, 53).addBox(-1.36914F, -1.50286F, -1F, 2F, 10F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0.2F, 0.21817F, 0F, 0.04363F));
        PartDefinition left_leg = root.getChild("left_leg");
        PartDefinition left_leg_p0 = left_leg.addOrReplaceChild("legging_leftleg", CubeListBuilder.create()
                .texOffs(89, 54).addBox(-2F, -1.5F, -2F, 4F, 14F, 4F, new CubeDeformation(0.255F)), PartPose.offset(0.1F, 0F, 0F));
        PartDefinition left_leg_p0_0 = left_leg_p0.addOrReplaceChild("legging_leftleg_parts", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.2618F));
        PartDefinition left_leg_p0_0_0 = left_leg_p0_0.addOrReplaceChild("legging_leftleg_tasset", CubeListBuilder.create(), PartPose.offsetAndRotation(2.25F, -3F, 0F, 0F, 0F, -0.61087F));
        left_leg_p0_0_0.addOrReplaceChild("cube_4", CubeListBuilder.create()
                .texOffs(17, 98).mirror().addBox(-4.00865F, -2.13538F, -3F, 4F, 2F, 6F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-1F, 2F, 0F, 0F, 0F, 0.1309F));
        left_leg_p0_0_0.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(0, 96).mirror().addBox(-2.21388F, -3.40979F, -3F, 2F, 7F, 6F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-3.22073F, 3.95746F, 0F, 0F, 0F, 0.04363F));
        left_leg_p0_0_0.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(94, 29).mirror().addBox(-0.21388F, -3.40979F, -3F, 2F, 8F, 6F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(-3.22073F, 3.95746F, 0F, 0F, 0F, 0.04363F));
        PartDefinition left_leg_p0_0_0_3 = left_leg_p0_0_0.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.72307F, 1.47938F, -1.7F, 0F, -0.08727F, -0.17453F));
        left_leg_p0_0_0_3.addOrReplaceChild("cube_8", CubeListBuilder.create()
                .texOffs(72, 104).mirror().addBox(-0.63086F, -1.50286F, -1F, 2F, 10F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, -0.2F, -0.21817F, 0F, -0.04363F));
        PartDefinition left_leg_p0_0_0_4 = left_leg_p0_0_0.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.72307F, 1.47938F, 1.7F, 0F, 0.08727F, -0.17453F));
        left_leg_p0_0_0_4.addOrReplaceChild("cube_10", CubeListBuilder.create()
                .texOffs(106, 53).mirror().addBox(-0.63086F, -1.50286F, -1F, 2F, 10F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0.2F, 0.21817F, 0F, -0.04363F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    private static LayerDefinition createFeet() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        // Giu day du 7 part cua HumanoidModel, xoa cube vanilla de khong ve da/ao
        clearVanilla(root);
        PartDefinition right_leg = root.getChild("right_leg");
        PartDefinition right_leg_p0 = right_leg.addOrReplaceChild("boot_rightleg", CubeListBuilder.create()
                .texOffs(105, 6).mirror().addBox(-2F, 2.2F, -2F, 4F, 2F, 4F, new CubeDeformation(0.625F)).mirror(false)
                .texOffs(100, 99).mirror().addBox(-2F, 7.2F, -2F, 4F, 5F, 4F, new CubeDeformation(0.625F)).mirror(false), PartPose.offset(-0.1F, 0F, 0F));
        PartDefinition right_leg_p0_0 = right_leg_p0.addOrReplaceChild("boot_leftleg_parts2", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.7F, 9.4F, 0F, 0F, 0F, -0.2618F));
        right_leg_p0_0.addOrReplaceChild("cube_3", CubeListBuilder.create()
                .texOffs(0, 110).mirror().addBox(-1F, -1.5F, -1.5F, 2F, 3F, 3F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        right_leg_p0_0.addOrReplaceChild("cube_4", CubeListBuilder.create()
                .texOffs(33, 70).mirror().addBox(-2F, -1F, -1F, 1F, 2F, 2F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0.5F, 0F, 0F, 0.7854F, 0F, 0F));
        right_leg_p0_0.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(89, 73).mirror().addBox(-1F, -2.5F, 1.5F, 0F, 1F, 1F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        right_leg_p0_0.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(58, 81).mirror().addBox(-1F, 0.5F, 1.5F, 1F, 1F, 1F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        right_leg_p0_0.addOrReplaceChild("cube_7", CubeListBuilder.create()
                .texOffs(58, 84).mirror().addBox(-1F, -2.5F, -1.5F, 1F, 1F, 1F, CubeDeformation.NONE).mirror(false), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        PartDefinition left_leg = root.getChild("left_leg");
        PartDefinition left_leg_p0 = left_leg.addOrReplaceChild("boot_leftleg", CubeListBuilder.create()
                .texOffs(105, 6).addBox(-2F, 2.2F, -2F, 4F, 2F, 4F, new CubeDeformation(0.625F))
                .texOffs(100, 99).addBox(-2F, 7.2F, -2F, 4F, 5F, 4F, new CubeDeformation(0.625F)), PartPose.offset(0.1F, 0F, 0F));
        PartDefinition left_leg_p0_0 = left_leg_p0.addOrReplaceChild("boot_rightleg_parts2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.7F, 9.4F, 0F, 0F, 0F, 0.2618F));
        left_leg_p0_0.addOrReplaceChild("cube_3", CubeListBuilder.create()
                .texOffs(0, 110).addBox(-1F, -1.5F, -1.5F, 2F, 3F, 3F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        left_leg_p0_0.addOrReplaceChild("cube_4", CubeListBuilder.create()
                .texOffs(33, 70).addBox(1F, -1F, -1F, 1F, 2F, 2F, CubeDeformation.NONE), PartPose.offsetAndRotation(-0.5F, 0F, 0F, 0.7854F, 0F, 0F));
        left_leg_p0_0.addOrReplaceChild("cube_5", CubeListBuilder.create()
                .texOffs(89, 73).addBox(1F, -2.5F, 1.5F, 0F, 1F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        left_leg_p0_0.addOrReplaceChild("cube_6", CubeListBuilder.create()
                .texOffs(58, 81).addBox(0F, 0.5F, 1.5F, 1F, 1F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        left_leg_p0_0.addOrReplaceChild("cube_7", CubeListBuilder.create()
                .texOffs(58, 84).addBox(0F, -2.5F, -1.5F, 1F, 1F, 1F, CubeDeformation.NONE), PartPose.offsetAndRotation(0F, 0F, 0F, 0.7854F, 0F, 0F));
        return LayerDefinition.create(mesh, 128, 128);
    }
}
