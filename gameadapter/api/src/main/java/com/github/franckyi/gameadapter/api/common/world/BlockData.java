package com.github.franckyi.gameadapter.api.common.world;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public class BlockData {
    private IBlockState state;
    private ICompoundTag tag;

    public BlockData(IBlockState state, ICompoundTag tag) {
        this.tag = tag;
        this.state = state;
    }

    public IBlockState getState() {
        return state;
    }

    public void setState(IBlockState state) {
        this.state = state;
    }

    public ICompoundTag getTag() {
        return tag;
    }

    public void setTag(ICompoundTag tag) {
        this.tag = tag;
    }
}
