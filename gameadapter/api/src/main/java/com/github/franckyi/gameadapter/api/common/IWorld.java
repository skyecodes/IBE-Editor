package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public interface IWorld {
    void setBlockInventoryItem(IBlockPos blockPos, int slotId, IItemStack itemStack);

    BlockData getBlockData(IBlockPos blockPos);

    void setBlockData(WorldBlockData data);

    ICompoundTag getEntity(int entityId);

    void setEntityData(int entityId, ICompoundTag data);
}
