package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.util.common.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FabricPlayer implements Player {
    private final PlayerEntity entity;
    private Entity playerEntity;

    public FabricPlayer(PlayerEntity entity) {
        this.entity = entity;
    }

    @Override
    public Item getItemMainHand() {
        ItemStack item = entity.inventory.getMainHandStack();
        return item.isEmpty() ? null : new FabricItem(item);
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public void sendMessage(Text message, boolean actionBar) {
        entity.sendMessage(FabricTextFactory.INSTANCE.create(message), actionBar);
    }

    @Override
    public Entity getPlayerEntity() {
        if (playerEntity == null) {
            playerEntity = new FabricEntity(entity);
        }
        return playerEntity;
    }
}
