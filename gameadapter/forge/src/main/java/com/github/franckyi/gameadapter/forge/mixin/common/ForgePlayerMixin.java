package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.IWorld;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class ForgePlayerMixin extends LivingEntity implements IPlayer {
    @Shadow
    @Final
    public PlayerInventory inventory;

    @Shadow
    public abstract GameProfile getGameProfile();

    @Shadow
    public abstract void displayClientMessage(ITextComponent p_146105_1_, boolean p_146105_2_);

    protected ForgePlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public IItemStack getItemMainHand() {
        return IItemStack.class.cast(inventory.getSelected());
    }

    @Override
    public void setItemMainHand(IItemStack itemStack) {
        setItemInHand(Hand.MAIN_HAND, ItemStack.class.cast(itemStack));
    }

    @Override
    public void setInventoryItem(IItemStack itemStack, int slotId) {
        inventory.setItem(slotId, ItemStack.class.cast(itemStack));
    }

    @Override
    public IWorld getWorld() {
        return (IWorld) getCommandSenderWorld();
    }

    @Override
    public UUID getProfileId() {
        return getGameProfile().getId();
    }

    @Override
    public String getProfileName() {
        return getGameProfile().getName();
    }

    @Override
    public void sendMessage(Text message, boolean actionBar) {
        displayClientMessage(message.get(), actionBar);
    }

    @Override
    public void updateMainHandItem(IItemStack itemStack) {
        Game.getClient().updateInventoryItem(itemStack, inventory.selected + inventory.items.size());
    }
}
