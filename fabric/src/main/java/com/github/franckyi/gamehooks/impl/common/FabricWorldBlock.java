package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class FabricWorldBlock implements WorldBlock {
    private final Pos pos;
    private final BlockEntity blockEntity;

    public FabricWorldBlock(Pos pos, BlockEntity blockEntity) {
        this.pos = pos;
        this.blockEntity = blockEntity;
    }

    @Override
    public Pos getPos() {
        return pos;
    }

    @Override
    public ObjectTag getTag() {
        return blockEntity == null ? null : FabricTagFactory.INSTANCE.parseCompound(blockEntity.toTag(new CompoundTag()));
    }
}
