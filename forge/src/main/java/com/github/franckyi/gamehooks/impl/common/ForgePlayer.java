package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.util.common.text.Text;
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
    public void sendMessage(Text message, boolean actionBar) {
        entity.sendStatusMessage(ForgeTextFactory.INSTANCE.create(message), actionBar);
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
    public World getWorld() {
        return new ForgeWorld(entity.getEntityWorld());
    }

    @Override
    public UUID getProfileId() {
        return entity.getGameProfile().getId();
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerEntity getPlayerEntity() {
        return entity;
    }
}
