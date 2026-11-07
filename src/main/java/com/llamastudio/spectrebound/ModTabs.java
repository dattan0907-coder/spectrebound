package com.llamastudio.spectrebound;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpectreBound.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrebound"))
                    .icon(() -> new ItemStack(ModItems.HELMET.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.HELMET.get());
                        output.accept(ModItems.CHESTPLATE.get());
                        output.accept(ModItems.LEGGINGS.get());
                        output.accept(ModItems.BOOTS.get());
                        output.accept(ModItems.DAGGER.get());
                        output.accept(ModItems.PLUSHIE.get());
                    })
                    .build());

    private ModTabs() {}
}
