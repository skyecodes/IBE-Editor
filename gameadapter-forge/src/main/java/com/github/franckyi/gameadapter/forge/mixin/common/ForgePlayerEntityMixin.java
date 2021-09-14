package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.IWorld;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;

import java.util.UUID;

@Mixin(PlayerEntity.class)
@Implements(@Interface(iface = IPlayer.class, prefix = "proxy$"))
public abstract class ForgePlayerEntityMixin extends LivingEntity {
    @Shadow
    @Final
    public PlayerInventory inventory;

    @Shadow
    public abstract GameProfile getGameProfile();

    @Shadow
    public abstract void displayClientMessage(ITextComponent p_146105_1_, boolean p_146105_2_);

    protected ForgePlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public IItemStack proxy$getItemMainHand() {
        return IItemStack.class.cast(inventory.getSelected());
    }

    public void proxy$setItemMainHand(IItemStack itemStack) {
        setItemInHand(Hand.MAIN_HAND, ItemStack.class.cast(itemStack));
    }

    public void proxy$setInventoryItem(IItemStack itemStack, int slotId) {
        inventory.setItem(slotId, ItemStack.class.cast(itemStack));
    }

    public IWorld proxy$getWorld() {
        return (IWorld) getCommandSenderWorld();
    }

    public UUID proxy$getProfileId() {
        return getGameProfile().getId();
    }

    public String proxy$getProfileName() {
        return getGameProfile().getName();
    }

    public void proxy$sendMessage(IText message, boolean actionBar) {
        displayClientMessage((ITextComponent) message, actionBar);
    }

    @Intrinsic
    public boolean proxy$isCreative() {
        return PlayerEntity.class.cast(this).isCreative();
    }

    public void proxy$updateMainHandItem(IItemStack itemStack) {
        Game.getClient().updateInventoryItem(itemStack, inventory.selected + inventory.items.size());
    }
}
