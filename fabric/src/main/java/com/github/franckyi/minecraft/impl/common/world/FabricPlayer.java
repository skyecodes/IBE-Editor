package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.world.World;
import com.github.franckyi.minecraft.api.common.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.UUID;

public class FabricPlayer extends FabricWorldEntity implements Player {
    private final PlayerEntity entity;

    public FabricPlayer(PlayerEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public Item getItemMainHand() {
        ItemStack item = entity.inventory.getMainHandStack();
        return item.isEmpty() ? null : new FabricItem(item);
    }

    @Override
    public void setItemMainHand(Item item) {
        entity.setStackInHand(Hand.MAIN_HAND, item.get());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setStack(slotId, item.get());
    }

    @Override
    public World getWorld() {
        return new FabricWorld(entity.getEntityWorld());
    }

    @Override
    public UUID getProfileId() {
        return entity.getGameProfile().getId();
    }

    @Override
    public String toString() {
        return entity.getGameProfile().getName();
    }

    @Override
    public void sendMessage(Text message, boolean actionBar) {
        entity.sendMessage(message.get(), actionBar);
    }

    @Override
    public boolean isCreative() {
        return entity.isCreative();
    }

    @Override
    public void updateMainHandItem(Item item) {
        updateInventoryItem(item.get(), entity.inventory.selectedSlot + entity.inventory.main.size());
    }

    @Override
    public void updateCreativeInventoryItem(Item item, int slotId) {
        entity.inventory.setStack(slotId, item.get());
    }

    @Override
    public void updateInventoryItem(Item item, int slotId) {
        MinecraftClient.getInstance().interactionManager.clickCreativeStack(item.get(), slotId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerEntity get() {
        return entity;
    }
}
