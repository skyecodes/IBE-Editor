package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public class WorldBlockData extends BlockData {
    private IBlockPos pos;

    public WorldBlockData(BlockData data, IBlockPos pos) {
        this(data.getState(), data.getTag(), pos);
    }

    public WorldBlockData(IBlockState state, ICompoundTag tag, IBlockPos pos) {
        super(state, tag);
        this.pos = pos;
    }

    public IBlockPos getPos() {
        return pos;
    }

    public void setPos(IBlockPos pos) {
        this.pos = pos;
    }
}
