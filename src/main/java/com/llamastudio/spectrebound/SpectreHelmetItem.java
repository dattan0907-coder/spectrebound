package com.llamastudio.spectrebound;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Mu pho thuy. Khong phai ArmorItem: vanilla se ve item model 3D (spectrebound_helmet.json)
 * len dau qua CustomHeadLayer, giong cach Nexo dung model rieng cho non.
 * Chi so giap duoc khai bao bang ItemAttributeModifiers trong ModItems.
 */
public class SpectreHelmetItem extends Item implements Equipable {
    public SpectreHelmetItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return this.swapWithEquipmentSlot(this, level, player, hand);
    }
}
