package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IBlockState;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraftforge.common.extensions.IForgeBlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public abstract class ForgeBlockStateMixin extends AbstractBlock.AbstractBlockState implements IForgeBlockState, IBlockState {
    protected ForgeBlockStateMixin(Block p_i231870_1_, ImmutableMap<Property<?>, Comparable<?>> p_i231870_2_, MapCodec<BlockState> p_i231870_3_) {
        super(p_i231870_1_, p_i231870_2_, p_i231870_3_);
    }
}
