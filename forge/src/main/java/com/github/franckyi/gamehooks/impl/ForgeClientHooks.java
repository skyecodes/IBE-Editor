package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenHandler;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.api.common.WorldEntity;
import com.github.franckyi.gamehooks.impl.client.ForgeRenderer;
import com.github.franckyi.gamehooks.impl.client.ForgeScreenScaling;
import com.github.franckyi.gamehooks.impl.common.ForgePlayer;
import com.github.franckyi.gamehooks.impl.common.ForgeBlockPos;
import com.github.franckyi.gamehooks.impl.common.ForgeWorld;
import com.github.franckyi.gamehooks.impl.common.ForgeWorldEntity;
import com.github.franckyi.gamehooks.impl.client.ForgeScreenHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();

    private ForgeClientHooks() {
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
