package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.world.IWorld;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.*;

import java.util.UUID;

@Mixin(Player.class)
@Implements(@Interface(iface = IPlayer.class, prefix = "proxy$"))
public abstract class ForgePlayerEntityMixin extends LivingEntity {
    @Shadow
    @Final
    private Inventory inventory;

    @Shadow
    public abstract GameProfile getGameProfile();

    @Shadow
    public abstract void displayClientMessage(Component p_146105_1_, boolean p_146105_2_);

    protected ForgePlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    public IItemStack proxy$getItemMainHand() {
        return IItemStack.class.cast(inventory.getSelected());
    }

    public void proxy$setItemMainHand(IItemStack itemStack) {
        setItemInHand(InteractionHand.MAIN_HAND, ItemStack.class.cast(itemStack));
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
        displayClientMessage((Component) message, actionBar);
    }

    @Intrinsic
    public boolean proxy$isCreative() {
        return Player.class.cast(this).isCreative();
    }

    public void proxy$updateMainHandItem(IItemStack itemStack) {
        Game.getClient().updateInventoryItem(itemStack, inventory.selected + inventory.items.size());
    }
}
