package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.BlockPos;

public class FabricBlockPos implements BlockPos {
    private final net.minecraft.util.math.BlockPos blockPos;

    public FabricBlockPos(net.minecraft.util.math.BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    @Override
    @SuppressWarnings("unchecked")
    public net.minecraft.util.math.BlockPos get() {
        return blockPos;
    }
}
