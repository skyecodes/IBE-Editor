package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.client.KeyBinding;
import com.github.franckyi.gameadapter.api.client.render.Renderer;
import com.github.franckyi.gameadapter.api.client.screen.ScreenScaling;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.gameadapter.api.common.world.World;
import com.github.franckyi.gameadapter.api.common.world.WorldBlock;
import com.github.franckyi.gameadapter.api.common.world.WorldEntity;
import com.github.franckyi.gameadapter.fabric.client.render.FabricMatrices;
import com.github.franckyi.gameadapter.fabric.client.render.FabricRenderer;
import com.github.franckyi.gameadapter.fabric.client.screen.FabricScreen;
import com.github.franckyi.gameadapter.fabric.client.screen.FabricScreenScaling;
import com.github.franckyi.gameadapter.fabric.common.FabricBlockPos;
import com.github.franckyi.gameadapter.fabric.common.world.FabricPlayer;
import com.github.franckyi.gameadapter.fabric.common.world.FabricWorld;
import com.github.franckyi.gameadapter.fabric.common.world.FabricWorldEntity;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public final class FabricGameClient implements GameClient {
    public static final GameClient INSTANCE = new FabricGameClient();

    private FabricGameClient() {
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
    @SuppressWarnings("unchecked")
    public MatricesFactory<MatrixStack> getMatricesFactory() {
        return FabricMatrices::of;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScreenFactory<Screen> getScreenFactory() {
        return FabricScreen::new;
    }

    @Override
    public void unlockCursor() {
        net.minecraft.client.MinecraftClient.getInstance().mouse.unlockCursor();
    }
}
