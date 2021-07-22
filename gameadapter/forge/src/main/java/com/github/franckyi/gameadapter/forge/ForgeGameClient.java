package com.github.franckyi.gameadapter.forge;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.client.KeyBinding;
import com.github.franckyi.gameadapter.api.client.render.Renderer;
import com.github.franckyi.gameadapter.api.client.screen.ScreenScaling;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.gameadapter.api.common.world.World;
import com.github.franckyi.gameadapter.api.common.world.WorldBlock;
import com.github.franckyi.gameadapter.api.common.world.WorldEntity;
import com.github.franckyi.gameadapter.forge.client.render.ForgeMatrices;
import com.github.franckyi.gameadapter.forge.client.render.ForgeRenderer;
import com.github.franckyi.gameadapter.forge.client.screen.ForgeScreen;
import com.github.franckyi.gameadapter.forge.client.screen.ForgeScreenScaling;
import com.github.franckyi.gameadapter.forge.common.ForgeBlockPos;
import com.github.franckyi.gameadapter.forge.common.world.ForgePlayer;
import com.github.franckyi.gameadapter.forge.common.world.ForgeWorld;
import com.github.franckyi.gameadapter.forge.common.world.ForgeWorldEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeGameClient implements GameClient {
    public static final GameClient INSTANCE = new ForgeGameClient();

    private ForgeGameClient() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public Renderer getRenderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public ScreenScaling getScreenScaling() {
        return ForgeScreenScaling.INSTANCE;
    }

    @Override
    public KeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.settings.KeyBinding keyBinding = new net.minecraft.client.settings.KeyBinding(name, keyCode, category);
        ClientRegistry.registerKeyBinding(keyBinding);
        return new KeyBinding() {
            @Override
            public boolean isPressed() {
                return keyBinding.isDown();
            }

            @Override
            public int getKeyCode() {
                return keyBinding.getKey().getValue();
            }
        };
    }

    @Override
    public Player getPlayer() {
        return new ForgePlayer(mc().player);
    }

    @Override
    public World getWorld() {
        return new ForgeWorld(mc().level);
    }

    @Override
    public WorldEntity getEntityMouseOver() {
        RayTraceResult result = mc().hitResult;
        if (result instanceof EntityRayTraceResult) {
            return new ForgeWorldEntity(((EntityRayTraceResult) result).getEntity());
        }
        return null;
    }

    @Override
    public WorldBlock getBlockMouseOver() {
        RayTraceResult result = mc().hitResult;
        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult res = (BlockRayTraceResult) result;
            if (!mc().level.isEmptyBlock(res.getBlockPos())) {
                return getWorld().getBlock(new ForgeBlockPos(res.getBlockPos()));
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MatricesFactory<MatrixStack> getMatricesFactory() {
        return ForgeMatrices::of;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScreenFactory<Screen> getScreenFactory() {
        return ForgeScreen::new;
    }

    @Override
    public void unlockCursor() {
        mc().mouseHandler.releaseMouse();
    }
}
