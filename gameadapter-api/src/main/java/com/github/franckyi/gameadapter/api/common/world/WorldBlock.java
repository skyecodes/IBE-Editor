package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.BlockPos;

public interface WorldBlock extends Block {
    BlockPos getBlockPos();
}
