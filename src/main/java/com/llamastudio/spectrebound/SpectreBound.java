package com.llamastudio.spectrebound;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SpectreBound.MODID)
public class SpectreBound {
    public static final String MODID = "spectrebound";

    public SpectreBound(IEventBus modBus) {
        ModArmorMaterials.ARMOR_MATERIALS.register(modBus);
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModTabs.TABS.register(modBus);
    }
}
