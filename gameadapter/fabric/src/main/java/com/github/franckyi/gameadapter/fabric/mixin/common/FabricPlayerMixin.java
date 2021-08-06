package com.github.franckyi.gameadapter.fabric.mixin.common;

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
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class FabricPlayerMixin extends LivingEntity implements IPlayer {
    @Shadow
    @Final
    public PlayerInventory inventory;

    @Shadow
    public abstract GameProfile getGameProfile();

    @Shadow
    public abstract void shadow$sendMessage(net.minecraft.text.Text message, boolean actionBar);

    protected FabricPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public IItemStack getItemMainHand() {
        return IItemStack.class.cast(inventory.getMainHandStack());
    }

    @Override
    public void setItemMainHand(IItemStack itemStack) {
        setStackInHand(Hand.MAIN_HAND, ItemStack.class.cast(itemStack));
    }

    @Override
    public void setInventoryItem(IItemStack itemStack, int slotId) {
        inventory.setStack(slotId, ItemStack.class.cast(itemStack));
    }

    @Override
    public IWorld getWorld() {
        return (IWorld) getEntityWorld();
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
        shadow$sendMessage(message.get(), actionBar);
    }

    @Override
    public void updateMainHandItem(IItemStack itemStack) {
        Game.getClient().updateInventoryItem(itemStack, inventory.selectedSlot + inventory.main.size());
    }
}
