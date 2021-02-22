package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.ClientPlayer;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.impl.client.ForgeClientPlayer;
import com.github.franckyi.gamehooks.impl.client.ForgeFontRenderer;
import com.github.franckyi.gamehooks.impl.client.ForgeShapeRenderer;
import com.github.franckyi.gamehooks.impl.common.ForgeBlock;
import com.github.franckyi.gamehooks.impl.common.ForgeEntity;
import com.github.franckyi.gamehooks.impl.common.ForgePos;
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
    @SuppressWarnings("unchecked")
    public FontRenderer<?, ?> fontRenderer() {
        return ForgeFontRenderer.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ShapeRenderer<?> shapeRenderer() {
        return ForgeShapeRenderer.INSTANCE;
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
    public ClientPlayer player() {
        return ForgeClientPlayer.INSTANCE;
    }

    @Override
    public Entity entityMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof EntityRayTraceResult) {
            return new ForgeEntity(((EntityRayTraceResult) result).getEntity());
        }
        return null;
    }

    @Override
    public Block blockMouseOver() {
        RayTraceResult result = mc().objectMouseOver;
        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult res = (BlockRayTraceResult) result;
            if (!mc().world.isAirBlock(res.getPos())) {
                return new ForgeBlock(new ForgePos(res.getPos()));
            }
        }
        return null;
    }

    @Override
    public void unlockCursor() {
        mc().mouseHelper.ungrabMouse();
    }
}
