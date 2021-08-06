package com.github.franckyi.gameadapter.forge;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.*;
import com.github.franckyi.gameadapter.forge.client.ForgeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public final class ForgeGameClient implements GameClient {
    public static final GameClient INSTANCE = new ForgeGameClient();

    private ForgeGameClient() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public IRenderer getRenderer() {
        return ForgeRenderer.INSTANCE;
    }

    @Override
    public IKeyBinding registerKeyBinding(String name, int keyCode, String category) {
        net.minecraft.client.settings.KeyBinding keyBinding = new net.minecraft.client.settings.KeyBinding(name, keyCode, category);
        ClientRegistry.registerKeyBinding(keyBinding);
        return (IKeyBinding) keyBinding;
    }

    @Override
    public IPlayer getPlayer() {
        return (IPlayer) mc().player;
    }

    @Override
    public IWorld getWorld() {
        return (IWorld) mc().level;
    }

    @Override
    public IEntity getEntityMouseOver() {
        RayTraceResult result = mc().hitResult;
        if (result instanceof EntityRayTraceResult) {
            return (IEntity) ((EntityRayTraceResult) result).getEntity();
        }
        return null;
    }

    @Override
    public WorldBlockData getBlockMouseOver() {
        RayTraceResult result = mc().hitResult;
        if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult res = (BlockRayTraceResult) result;
            if (!mc().level.isEmptyBlock(res.getBlockPos())) {
                IBlockPos blockPos = (IBlockPos) res.getBlockPos();
                return new WorldBlockData(getWorld().getBlockData(blockPos), blockPos);
            }
        }
        return null;
    }

    @Override
    public ISprite getEffectSprite(IIdentifier effectId) {
        return (ISprite) Minecraft.getInstance().getMobEffectTextures()
                .get(ForgeRegistries.POTIONS.getValue((ResourceLocation) effectId));
    }

    @Override
    public void updateInventoryItem(IItemStack itemStack, int slotId) {
        mc().gameMode.handleCreativeModeItemAdd(ItemStack.class.cast(itemStack), slotId);
    }

    @Override
    public void unlockCursor() {
        mc().mouseHandler.releaseMouse();
    }
}
