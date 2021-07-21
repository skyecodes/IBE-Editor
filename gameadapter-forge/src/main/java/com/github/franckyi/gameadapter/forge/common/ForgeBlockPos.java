package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.BlockPos;

public class ForgeBlockPos implements BlockPos {
    private final net.minecraft.util.math.BlockPos blockPos;

    public ForgeBlockPos(net.minecraft.util.math.BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.util.math.BlockPos get() {
        return blockPos;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
}
