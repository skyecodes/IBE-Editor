package com.github.franckyi.minecraft.api.common.world;

import com.github.franckyi.minecraft.api.common.BlockPos;

public interface WorldBlock extends Block {
    BlockPos getBlockPos();
}
