package com.github.franckyi.minecraft.impl;

import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.client.KeyBinding;
import com.github.franckyi.minecraft.api.client.render.Renderer;
import com.github.franckyi.minecraft.api.client.screen.ScreenHandler;
import com.github.franckyi.minecraft.api.client.screen.ScreenScaling;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.world.World;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;
import com.github.franckyi.minecraft.api.common.world.WorldEntity;
import com.github.franckyi.minecraft.impl.client.render.ForgeRenderer;
import com.github.franckyi.minecraft.impl.client.screen.ForgeScreenHandler;
import com.github.franckyi.minecraft.impl.client.screen.ForgeScreenScaling;
import com.github.franckyi.minecraft.impl.common.ForgeBlockPos;
import com.github.franckyi.minecraft.impl.common.world.ForgePlayer;
import com.github.franckyi.minecraft.impl.common.world.ForgeWorld;
import com.github.franckyi.minecraft.impl.common.world.ForgeWorldEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeMinecraftClient implements MinecraftClient {
    public static final MinecraftClient INSTANCE = new ForgeMinecraftClient();

    private ForgeMinecraftClient() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public Renderer getRenderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public ScreenHandler getScreenHandler() {
        return ForgeScreenHandler.INSTANCE;
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
                return keyBinding.isPressed();
            }

            @Override
            public int getKeyCode() {
                return keyBinding.getKey().getKeyCode();
            }
        };
    }

    @Override
    public Player getPlayer() {
        return new ForgePlayer(mc().player);
    }

    @Override
    public World getWorld() {
        return new ForgeWorld(mc().world);
    }

    @Override
    public WorldEntity getEntityMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof EntityRayTraceResult) {
            return new ForgeWorldEntity(((EntityRayTraceResult) result).getEntity());
        }
        return null;
    }

    @Override
    public WorldBlock getBlockMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult res = (BlockRayTraceResult) result;
            if (!mc().world.isAirBlock(res.getPos())) {
                return getWorld().getBlock(new ForgeBlockPos(res.getPos()));
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        mc().mouseHelper.ungrabMouse();
    }
}
