package com.github.franckyi.gamehooks.api.common;

public interface World {
    void setBlockInventoryItem(Pos pos, int slotId, Item item);

    WorldBlock getBlock(Pos pos);

    void setBlockData(Pos pos, Block block);

    WorldEntity getEntity(int entityId);

    void setEntityData(int entityId, Entity entity);
}
