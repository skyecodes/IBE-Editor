package com.github.franckyi.minecraft.impl;

import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.client.KeyBinding;
import com.github.franckyi.minecraft.api.client.render.Renderer;
import com.github.franckyi.minecraft.api.client.screen.ScreenScaling;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.world.World;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;
import com.github.franckyi.minecraft.api.common.world.WorldEntity;
import com.github.franckyi.minecraft.impl.client.render.FabricRenderer;
import com.github.franckyi.minecraft.impl.client.screen.FabricScreenScaling;
import com.github.franckyi.minecraft.impl.common.FabricBlockPos;
import com.github.franckyi.minecraft.impl.common.world.FabricPlayer;
import com.github.franckyi.minecraft.impl.common.world.FabricWorld;
import com.github.franckyi.minecraft.impl.common.world.FabricWorldEntity;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public final class FabricMinecraftClient implements MinecraftClient {
    public static final MinecraftClient INSTANCE = new FabricMinecraftClient();

    private FabricMinecraftClient() {
    }

    private net.minecraft.client.MinecraftClient mc() {
        return net.minecraft.client.MinecraftClient.getInstance();
    }

    @Override
    public Renderer getRenderer() {
        return FabricRenderer.INSTANCE;
    }

    @Override
    public ScreenScaling getScreenScaling() {
        return FabricScreenScaling.INSTANCE;
    }

    @Override
    public KeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.option.KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new net.minecraft.client.option.KeyBinding(name, keyCode, category));
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
                return getWorld().getBlock(new FabricBlockPos(res.getBlockPos()));
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        net.minecraft.client.MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
