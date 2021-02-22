package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Pos;
import net.minecraft.util.math.BlockPos;

public class FabricPos implements Pos {
    private final BlockPos pos;

    public FabricPos(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BlockPos getPos() {
        return pos;
    }
}
