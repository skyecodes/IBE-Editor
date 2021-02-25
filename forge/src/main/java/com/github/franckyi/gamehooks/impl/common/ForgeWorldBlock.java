package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class ForgeWorldBlock implements WorldBlock {
    private final Pos pos;
    private final TileEntity tileEntity;

    public ForgeWorldBlock(Pos pos, TileEntity tileEntity) {
        this.pos = pos;
        this.tileEntity = tileEntity;
    }

    @Override
    public Pos getPos() {
        return pos;
    }

    @Override
    public ObjectTag getTag() {
        return tileEntity == null ? null : ForgeTagFactory.parseCompound(tileEntity.write(new CompoundNBT()));
    }
}
