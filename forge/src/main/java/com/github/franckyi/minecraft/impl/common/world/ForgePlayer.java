package com.github.franckyi.minecraft.impl.common.world;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.UUID;

public class ForgePlayer extends ForgeWorldEntity implements Player {
    private final PlayerEntity entity;

    public ForgePlayer(PlayerEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public Item getItemMainHand() {
        ItemStack item = entity.inventory.getCurrentItem();
        return item.isEmpty() ? null : new ForgeItem(item);
    }

    @Override
    public void setItemMainHand(Item item) {
        entity.setHeldItem(Hand.MAIN_HAND, item.get());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setInventorySlotContents(slotId, item.get());
    }

    @Override
    public World getWorld() {
        return new ForgeWorld(entity.getEntityWorld());
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
        entity.sendStatusMessage(message.get(), actionBar);
    }

    @Override
    public boolean isCreative() {
        return entity.isCreative();
    }

    @Override
    public void updateMainHandItem(Item item) {
        updateInventoryItem(item, entity.inventory.currentItem + entity.inventory.mainInventory.size());
    }

    @Override
    public void updateCreativeInventoryItem(Item item, int slotId) {
        entity.inventory.setInventorySlotContents(slotId, item.get());
    }

    @Override
    public void updateInventoryItem(Item item, int slotId) {
        Minecraft.getInstance().playerController.sendSlotPacket(item.get(), slotId < 9 ? slotId + entity.inventory.mainInventory.size() : slotId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerEntity get() {
        return entity;
    }
}
