package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import com.github.franckyi.gameadapter.api.common.world.IWorld;

import java.util.UUID;

public interface IPlayer extends IEntity {
    static IPlayer client() {
        return Game.getClient().getPlayer();
    }

    IItemStack getItemMainHand();

    void setItemMainHand(IItemStack itemStack);

    void setInventoryItem(IItemStack itemStack, int slotId);

    IWorld getWorld();

    UUID getProfileId();

    String getProfileName();

    void sendMessage(IText message, boolean actionBar);

    default void sendMessage(IText message) {
        sendMessage(message, false);
    }

    boolean isCreative();

    void updateMainHandItem(IItemStack itemStack);
}
