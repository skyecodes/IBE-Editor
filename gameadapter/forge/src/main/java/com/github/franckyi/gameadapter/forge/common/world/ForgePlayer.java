package com.github.franckyi.gameadapter.forge.common.world;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.gameadapter.api.common.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
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
        net.minecraft.item.ItemStack item = entity.inventory.getSelected();
        return item.isEmpty() ? null : new ForgeItem(item);
    }

    @Override
    public void setItemMainHand(Item item) {
        entity.setItemInHand(Hand.MAIN_HAND, item.get());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setItem(slotId, item.get());
    }

    @Override
    public World getWorld() {
        return new ForgeWorld(entity.getCommandSenderWorld());
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
        entity.displayClientMessage(message.get(), actionBar);
    }

    @Override
    public boolean isCreative() {
        return entity.isCreative();
    }

    @Override
    public void updateMainHandItem(Item item) {
        updateInventoryItem(item, entity.inventory.selected + entity.inventory.items.size());
    }

    @Override
    public void updateCreativeInventoryItem(Item item, int slotId) {
        entity.inventory.setItem(slotId, item.get());
    }

    @Override
    public void updateInventoryItem(Item item, int slotId) {
        Minecraft.getInstance().gameMode.handleCreativeModeItemAdd(item.get(), slotId < 9 ? slotId + entity.inventory.items.size() : slotId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerEntity get() {
        return entity;
    }
}
