package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.api.common.WorldEntity;
import com.github.franckyi.gamehooks.impl.client.FabricRenderer;
import com.github.franckyi.gamehooks.impl.client.FabricScreenScaling;
import com.github.franckyi.gamehooks.impl.common.FabricPlayer;
import com.github.franckyi.gamehooks.impl.common.FabricPos;
import com.github.franckyi.gamehooks.impl.common.FabricWorld;
import com.github.franckyi.gamehooks.impl.common.FabricWorldEntity;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public final class FabricClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new FabricClientHooks();

    private FabricClientHooks() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer getRenderer() {
        return FabricRenderer.INSTANCE;
    }

    @Override
    public ScreenScaling getScreenScaling() {
        return FabricScreenScaling.INSTANCE;
    }

    @Override
    public KeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.options.KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.options.KeyBinding(name, keyCode, category));
        return new KeyBinding() {
            @Override
            public boolean isPressed() {
                return keyBinding.wasPressed();
            }

            @Override
            public int getKeyCode() {
                return KeyBindingHelper.getBoundKeyOf(keyBinding).getCode();
            }
        };
    }

    @Override
    public Player getPlayer() {
        return new FabricPlayer(mc().player);
    }

    @Override
    public World getWorld() {
        return new FabricWorld(mc().world);
    }

    @Override
    public WorldEntity getEntityMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof EntityHitResult) {
            return new FabricWorldEntity(((EntityHitResult) result).getEntity());
        }
        return null;
    }

    @Override
    public WorldBlock getBlockMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof BlockHitResult) {
            BlockHitResult res = (BlockHitResult) result;
            if (!mc().world.isAir(res.getBlockPos())) {
                return getWorld().getBlock(new FabricPos(res.getBlockPos()));
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
