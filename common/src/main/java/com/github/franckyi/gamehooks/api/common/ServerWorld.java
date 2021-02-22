package com.github.franckyi.gamehooks.api.common;

import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public interface ServerWorld {
    void setBlockInventoryItem(Pos pos, int slotId, Item item);

    void setEntityData(int entityId, ObjectTag tag);

    Block getBlock(Pos pos);

    void setBlockData(Pos pos, ObjectTag tag);
}
