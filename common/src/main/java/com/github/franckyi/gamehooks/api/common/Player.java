package com.github.franckyi.gamehooks.api.common;

import java.util.UUID;

public interface Player extends WorldEntity {
    Item getItemMainHand();

    void setItemMainHand(Item item);

    void setInventoryItem(int slotId, Item item);

    World getWorld();

    UUID getProfileId();

    String getName();

    void sendMessage(Text message, boolean actionBar);

    default void sendMessage(Text message) {
        sendMessage(message, false);
    }

    <E> E get();
}
