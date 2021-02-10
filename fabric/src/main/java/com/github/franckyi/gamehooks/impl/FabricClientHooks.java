package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.ClientPlayer;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.impl.client.FabricClientPlayer;
import com.github.franckyi.gamehooks.impl.client.FabricFontRenderer;
import com.github.franckyi.gamehooks.impl.client.FabricShapeRenderer;
import com.github.franckyi.gamehooks.impl.common.FabricBlock;
import com.github.franckyi.gamehooks.impl.common.FabricEntity;
import com.github.franckyi.gamehooks.impl.common.FabricPlayer;
import com.github.franckyi.gamehooks.impl.common.FabricWorld;
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
    public FontRenderer<?, ?> fontRenderer() {
        return FabricFontRenderer.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeRenderer<?> shapeRenderer() {
        return FabricShapeRenderer.INSTANCE;
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
    public ClientPlayer player() {
        return FabricClientPlayer.INSTANCE;
    }

    @Override
    public Entity entityMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof EntityHitResult) {
            return new FabricEntity(((EntityHitResult) result).getEntity());
        }
        return null;
    }

    @Override
    public Block blockMouseOver() {
        HitResult result = mc().crosshairTarget;
        if (result instanceof BlockHitResult) {
            BlockHitResult res = (BlockHitResult) result;
            if (!mc().world.isAir(res.getBlockPos())) {
                return new FabricBlock(res.getBlockPos());
            }
        }
        return null;
    }

    @Override
    public World world() {
        return FabricWorld.INSTANCE;
    }

    @Override
    public void unlockCursor() {
        MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
