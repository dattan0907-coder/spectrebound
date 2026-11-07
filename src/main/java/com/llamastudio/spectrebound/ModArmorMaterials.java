package com.llamastudio.spectrebound;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, SpectreBound.MODID);

    /** He so do ben (netherite = 37). */
    public static final int DURABILITY_FACTOR = 37;

    /**
     * Chi so giap giong netherite. Sua so o day neu muon doi.
     * Layer "spectrebound" -> textures/models/armor/spectrebound_layer_1.png (+ _layer_2.png cho quan).
     */
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SPECTREBOUND =
            ARMOR_MATERIALS.register("spectrebound", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }),
                    15,
                    SoundEvents.ARMOR_EQUIP_NETHERITE,
                    () -> Ingredient.of(Items.NETHERITE_INGOT),
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(SpectreBound.MODID, "spectrebound"))),
                    3.0F,
                    0.1F));

    private ModArmorMaterials() {}
}
