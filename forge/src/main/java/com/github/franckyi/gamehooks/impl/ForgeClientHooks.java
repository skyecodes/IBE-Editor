package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.impl.client.ForgeRenderer;
import com.github.franckyi.gamehooks.impl.client.ForgeScreenScaling;
import com.github.franckyi.gamehooks.impl.common.ForgePlayer;
import com.github.franckyi.gamehooks.impl.common.ForgePos;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public final class ForgeClientHooks implements ClientHooks {
    public static final ClientHooks INSTANCE = new ForgeClientHooks();
    private Player playerInstance;

    private ForgeClientHooks() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Renderer<?, ?> renderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public ScreenScaling screen() {
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
    public Player player() {
        if (playerInstance == null) {
            playerInstance = new ForgePlayer(mc().player);
        }
        return playerInstance;
    }

    @Override
    public int entityIdMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof EntityRayTraceResult) {
            return ((EntityRayTraceResult) result).getEntity().getEntityId();
        }
        return -1;
    }

    @Override
    public Pos blockPosMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult res = (BlockRayTraceResult) result;
            if (!mc().world.isAirBlock(res.getPos())) {
                return new ForgePos(res.getPos());
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        mc().mouseHelper.ungrabMouse();
    }
}
