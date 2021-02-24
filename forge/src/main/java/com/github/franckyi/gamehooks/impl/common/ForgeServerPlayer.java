package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;

import java.util.UUID;

public class ForgeServerPlayer extends ForgePlayer implements ServerPlayer {
    private final ServerPlayerEntity entity;
    private ServerWorld world;

    public ForgeServerPlayer(ServerPlayerEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void setItemMainHand(Item item) {
        entity.setHeldItem(Hand.MAIN_HAND, item.getStack());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setInventorySlotContents(slotId, item.getStack());
    }

    @Override
    public ServerWorld getWorld() {
        if (world == null) {
            world = new ForgeServerWorld(entity.getServerWorld());
        }
        return world;
    }

    @Override
    public UUID getProfileId() {
        return entity.getGameProfile().getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ServerPlayerEntity getServerEntity() {
        return entity;
    }
}
