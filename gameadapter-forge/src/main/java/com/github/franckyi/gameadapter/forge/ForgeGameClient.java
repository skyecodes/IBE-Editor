package com.github.franckyi.gameadapter.forge;

import com.github.franckyi.gameadapter.api.GameClient;
import com.github.franckyi.gameadapter.api.client.IKeyBinding;
import com.github.franckyi.gameadapter.api.client.IRenderer;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.IEntity;
import com.github.franckyi.gameadapter.api.common.world.IWorld;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.gameadapter.forge.client.ForgeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
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
        net.minecraft.client.KeyMapping keyBinding = new net.minecraft.client.KeyMapping(name, keyCode, category);
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
        HitResult result = mc().hitResult;
        if (result instanceof EntityHitResult res) {
            return (IEntity) res.getEntity();
        }
        return null;
    }

    @Override
    public WorldBlockData getBlockMouseOver() {
        HitResult result = mc().hitResult;
        if (result instanceof BlockHitResult res) {
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
                .get(ForgeRegistries.MOB_EFFECTS.getValue((ResourceLocation) effectId));
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
