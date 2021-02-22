package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class ForgeBlock implements Block {
    private TileEntity tileEntity;
    private ObjectTag tag;

    public ForgeBlock(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    public ForgeBlock(ObjectTag tag) {
        this.tag = tag;
    }

    @Override
    public ObjectTag getTag() {
        if (tag == null && tileEntity != null) {
            tag = ForgeTagFactory.INSTANCE.parseCompound(tileEntity.write(new CompoundNBT()));
        }
        return tag;
    }
}
