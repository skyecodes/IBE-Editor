package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface World {
    void setBlockInventoryItem(Pos pos, int slotId, Item item);

    WorldBlock getBlock(Pos pos);

    void setBlockData(Pos pos, ObjectTag tag);

    WorldEntity getEntity(int entityId);

    void setEntityData(int entityId, ObjectTag tag);
}
