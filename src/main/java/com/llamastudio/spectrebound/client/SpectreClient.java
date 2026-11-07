package com.llamastudio.spectrebound.client;

import java.util.EnumMap;
import java.util.Map;

import com.llamastudio.spectrebound.ModItems;
import com.llamastudio.spectrebound.SpectreBound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = SpectreBound.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class SpectreClient {
    /** Model da bake, xoa moi khi resource reload. */
    private static final Map<EquipmentSlot, SpectreArmorModel> CACHE = new EnumMap<>(EquipmentSlot.class);

    private SpectreClient() {}

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SpectreArmorModel.CHEST_LAYER, SpectreArmorModel::chestLayer);
        event.registerLayerDefinition(SpectreArmorModel.LEGS_LAYER, SpectreArmorModel::legsLayer);
        event.registerLayerDefinition(SpectreArmorModel.FEET_LAYER, SpectreArmorModel::feetLayer);
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        CACHE.clear();
    }

    @SubscribeEvent
    public static void registerExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            @SuppressWarnings({"unchecked", "rawtypes"})
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                          EquipmentSlot slot, HumanoidModel<?> original) {
                ModelLayerLocation layer = switch (slot) {
                    case CHEST -> SpectreArmorModel.CHEST_LAYER;
                    case LEGS -> SpectreArmorModel.LEGS_LAYER;
                    case FEET -> SpectreArmorModel.FEET_LAYER;
                    default -> null;
                };
                if (layer == null) return original;
                SpectreArmorModel model = CACHE.computeIfAbsent(slot,
                        s -> new SpectreArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(layer)));
                ((HumanoidModel) original).copyPropertiesTo(model);
                return model;
            }
        }, ModItems.CHESTPLATE.get(), ModItems.LEGGINGS.get(), ModItems.BOOTS.get());
    }
}
