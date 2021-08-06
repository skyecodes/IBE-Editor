package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.GameCommon;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.github.franckyi.gameadapter.fabric.common.FabricRegistryHandler;
import com.github.franckyi.gameadapter.fabric.common.FabricTagFactory;
import com.github.franckyi.gameadapter.fabric.common.FabricTextComponentFactory;
import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;
import java.util.function.Function;

public final class FabricGameCommon implements GameCommon {
    public static final GameCommon INSTANCE = new FabricGameCommon();

    private FabricGameCommon() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextComponentFactory<Text> getTextComponentFactory() {
        return FabricTextComponentFactory.INSTANCE;
    }

    @Override
    public TagFactory getTagFactory() {
        return FabricTagFactory.INSTANCE;
    }

    @Override
    public IItemStack createItemFromTag(ICompoundTag data) {
        return IItemStack.class.cast(ItemStack.fromNbt((NbtCompound) data));
    }

    @Override
    public IItemStack createItemFromId(IIdentifier id) {
        return IItemStack.class.cast(new ItemStack(Registry.ITEM.get((Identifier) id)));
    }

    @Override
    public Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    @Override
    public Command<ServerCommandSource> createCommand(Function<IPlayer, Integer> command) {
        return ctx -> command.apply((IPlayer) ctx.getSource().getPlayer());
    }

    @Override
    public RegistryHandler getRegistryHandler() {
        return FabricRegistryHandler.INSTANCE;
    }

    @Override
    public String translate(String key) {
        return Language.getInstance().get(key);
    }

    @Override
    public IIdentifier createIdentifier(String namespace, String path) {
        return (IIdentifier) new Identifier(namespace, path);
    }

    @Override
    public IIdentifier createIdentifier(String id) {
        return (IIdentifier) new Identifier(id);
    }

    @Override
    public IIdentifier parseIdentifier(String id) {
        return (IIdentifier) Identifier.tryParse(id);
    }
}
