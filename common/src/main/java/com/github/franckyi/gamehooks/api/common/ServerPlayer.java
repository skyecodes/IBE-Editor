package com.github.franckyi.gamehooks.api.common;

public interface ServerPlayer extends Player {
    void setItemMainHand(Item item);

    void setInventoryItem(int slotId, Item item);

    ServerWorld getWorld();

    <E> E getServerEntity();
}
