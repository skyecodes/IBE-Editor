package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.gamehooks.api.common.ServerWorld;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;

import java.util.UUID;

public class FabricServerPlayer extends FabricPlayer implements ServerPlayer {
    private final ServerPlayerEntity entity;
    private ServerWorld world;

    public FabricServerPlayer(ServerPlayerEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void setItemMainHand(Item item) {
        entity.setStackInHand(Hand.MAIN_HAND, item.getStack());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setStack(slotId, item.getStack());
    }

    @Override
    public ServerWorld getWorld() {
        if (world == null) {
            world = new FabricServerWorld(entity.getServerWorld());
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
