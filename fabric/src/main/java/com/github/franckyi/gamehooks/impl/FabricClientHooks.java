package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.impl.client.FabricRenderer;
import com.github.franckyi.gamehooks.impl.client.FabricScreenScaling;
import com.github.franckyi.gamehooks.impl.common.FabricPlayer;
import com.github.franckyi.gamehooks.impl.common.FabricPos;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public final class FabricClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new FabricClientHooks();
    private Player playerInstance;

    private FabricClientHooks() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<?, ?> renderer() {
        return FabricRenderer.INSTANCE;
    }

    @Override
    public ScreenScaling screen() {
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
    public Player player() {
        if (playerInstance == null) {
            playerInstance = new FabricPlayer(mc().player);
        }
        return playerInstance;
    }

    @Override
    public int entityIdMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof EntityHitResult) {
            return ((EntityHitResult) result).getEntity().getEntityId();
        }
        return -1;
    }

    @Override
    public Pos blockPosMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof BlockHitResult) {
            BlockHitResult res = (BlockHitResult) result;
            if (!mc().world.isAir(res.getBlockPos())) {
                return new FabricPos(res.getBlockPos());
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
