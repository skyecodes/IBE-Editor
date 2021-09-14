package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import com.github.franckyi.gameadapter.api.common.world.IWorld;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.gameadapter.fabric.client.FabricRenderer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;

public final class FabricGameClient implements GameClient {
    public static final GameClient INSTANCE = new FabricGameClient();

    private FabricGameClient() {
    }

    private net.minecraft.client.MinecraftClient mc() {
        return net.minecraft.client.MinecraftClient.getInstance();
    }

    @Override
    public IRenderer getRenderer() {
        return FabricRenderer.INSTANCE;
    }

    @Override
    public IKeyBinding registerKeyBinding(String name, int keyCode, String category) {
        return (IKeyBinding) KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.option.KeyBinding(name, keyCode, category));
    }

    @Override
    public IPlayer getPlayer() {
        return (IPlayer) mc().player;
    }

    @Override
    public IWorld getWorld() {
        return (IWorld) mc().world;
    }

    @Override
    public IEntity getEntityMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof EntityHitResult res) {
            return (IEntity) res.getEntity();
        }
        return null;
    }

    @Override
    public WorldBlockData getBlockMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof BlockHitResult res) {
            if (!mc().world.isAir(res.getBlockPos())) {
                IBlockPos blockPos = (IBlockPos) res.getBlockPos();
                return new WorldBlockData(getWorld().getBlockData(blockPos), blockPos);
            }
        }
        return null;
    }

    @Override
    public ISprite getEffectSprite(IIdentifier effectId) {
        return (ISprite) MinecraftClient.getInstance().getStatusEffectSpriteManager()
                .getSprite(Registry.STATUS_EFFECT.get((Identifier) effectId));
    }

    @Override
    public void updateInventoryItem(IItemStack itemStack, int slotId) {
        mc().interactionManager.clickCreativeStack(ItemStack.class.cast(itemStack), slotId);
    }

    @Override
    public void unlockCursor() {
        net.minecraft.client.MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
