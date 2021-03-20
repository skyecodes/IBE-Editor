package com.github.franckyi.minecraft.api.common.world;

import com.github.franckyi.minecraft.api.common.text.Text;

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

    boolean isCreative();

    void updateMainHandItem(Item item);

    void updateCreativeInventoryItem(Item item, int slotId);

    void updateInventoryItem(Item item, int slotId);

    <E> E get();
}
