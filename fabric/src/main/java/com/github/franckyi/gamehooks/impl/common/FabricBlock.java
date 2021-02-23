package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class FabricBlock implements Block {
    private final ObjectTag tag;

    public FabricBlock(BlockEntity blockEntity) {
        this(blockEntity == null ? null : FabricTagFactory.INSTANCE.parseCompound(blockEntity.toTag(new CompoundTag())));
    }

    public FabricBlock(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        return tag;
    }
}
