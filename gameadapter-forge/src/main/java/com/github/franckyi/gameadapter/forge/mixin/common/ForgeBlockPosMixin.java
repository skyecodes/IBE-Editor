package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockPos.class)
public abstract class ForgeBlockPosMixin extends Vector3i implements IBlockPos {
    protected ForgeBlockPosMixin(int p_i46007_1_, int p_i46007_2_, int p_i46007_3_) {
        super(p_i46007_1_, p_i46007_2_, p_i46007_3_);
    }
}
