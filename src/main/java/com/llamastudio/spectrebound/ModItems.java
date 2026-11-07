package com.llamastudio.spectrebound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpectreBound.MODID);

    private static Item.Properties armorProps(ArmorItem.Type type) {
        return new Item.Properties()
                .durability(type.getDurability(ModArmorMaterials.DURABILITY_FACTOR))
                .rarity(Rarity.EPIC)
                .fireResistant();
    }

    /** Mu: khai bao chi so giap bang attribute (giong netherite helmet). */
    private static Item.Properties helmetProps() {
        ItemAttributeModifiers attributes = ItemAttributeModifiers.builder()
                .add(Attributes.ARMOR, new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(SpectreBound.MODID, "armor.helmet"),
                        3.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
                .add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(SpectreBound.MODID, "armor.helmet"),
                        3.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
                .add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(SpectreBound.MODID, "armor.helmet"),
                        0.1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
                .build();
        return armorProps(ArmorItem.Type.HELMET).attributes(attributes);
    }

    public static final DeferredItem<SpectreHelmetItem> HELMET = ITEMS.register("spectrebound_helmet",
            () -> new SpectreHelmetItem(helmetProps()));

    public static final DeferredItem<ArmorItem> CHESTPLATE = ITEMS.register("spectrebound_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPECTREBOUND, ArmorItem.Type.CHESTPLATE,
                    armorProps(ArmorItem.Type.CHESTPLATE)));

    public static final DeferredItem<ArmorItem> LEGGINGS = ITEMS.register("spectrebound_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPECTREBOUND, ArmorItem.Type.LEGGINGS,
                    armorProps(ArmorItem.Type.LEGGINGS)));

    public static final DeferredItem<ArmorItem> BOOTS = ITEMS.register("spectrebound_boots",
            () -> new ArmorItem(ModArmorMaterials.SPECTREBOUND, ArmorItem.Type.BOOTS,
                    armorProps(ArmorItem.Type.BOOTS)));

    /** Dao: +2 sat thuong co ban, toc do danh nhanh (-1.8). Chinh so o day. */
    public static final DeferredItem<SwordItem> DAGGER = ITEMS.register("spectrebound_dagger",
            () -> new SwordItem(Tiers.NETHERITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -1.8F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<BlockItem> PLUSHIE = ITEMS.registerSimpleBlockItem(
            "spectrebound_plushie", ModBlocks.PLUSHIE);

    private ModItems() {}
}
