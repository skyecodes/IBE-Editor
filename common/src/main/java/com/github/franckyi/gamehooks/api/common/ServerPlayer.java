package com.github.franckyi.gamehooks.api.common;

import java.util.UUID;

public interface ServerPlayer extends Player {
    void setItemMainHand(Item item);

    void setInventoryItem(int slotId, Item item);

    ServerWorld getWorld();

    UUID getProfileId();

    <E> E getServerEntity();
}
