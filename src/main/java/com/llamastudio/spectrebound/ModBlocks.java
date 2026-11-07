package com.llamastudio.spectrebound;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SpectreBound.MODID);

    public static final DeferredBlock<PlushieBlock> PLUSHIE = BLOCKS.register("spectrebound_plushie",
            () -> new PlushieBlock(BlockBehaviour.Properties.of()
                    .strength(0.8F)
                    .sound(SoundType.WOOL)
                    .noOcclusion()));

    private ModBlocks() {}
}
