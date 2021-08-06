package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.text.Text;

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

    void sendMessage(Text message, boolean actionBar);

    default void sendMessage(Text message) {
        sendMessage(message, false);
    }

    boolean isCreative();

    void updateMainHandItem(IItemStack itemStack);
}
