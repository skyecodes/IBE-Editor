package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class ForgeBlock implements Block {
    private final ObjectTag tag;

    public ForgeBlock(TileEntity tileEntity) {
        this(tileEntity == null ? null : ForgeTagFactory.INSTANCE.parseCompound(tileEntity.write(new CompoundNBT())));
    }

    public ForgeBlock(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        return tag;
    }
}
