package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.util.common.text.Text;
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
        entity.setStackInHand(Hand.MAIN_HAND, item.getStack());
    }

    @Override
    public void setInventoryItem(int slotId, Item item) {
        entity.inventory.setStack(slotId, item.getStack());
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
    public void sendMessage(Text message, boolean actionBar) {
        entity.sendMessage(FabricTextFactory.INSTANCE.create(message), actionBar);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayerEntity getPlayerEntity() {
        return entity;
    }
}
