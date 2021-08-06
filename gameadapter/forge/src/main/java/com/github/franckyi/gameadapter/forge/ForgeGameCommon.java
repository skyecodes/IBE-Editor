package com.github.franckyi.gameadapter.forge;

import com.github.franckyi.gameadapter.api.GameCommon;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.github.franckyi.gameadapter.forge.common.ForgeRegistryHandler;
import com.github.franckyi.gameadapter.forge.common.ForgeTagFactory;
import com.github.franckyi.gameadapter.forge.common.ForgeTextComponentFactory;
import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public final class ForgeGameCommon implements GameCommon {
    public static final GameCommon INSTANCE = new ForgeGameCommon();

    private ForgeGameCommon() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextComponentFactory<ITextComponent> getTextComponentFactory() {
        return ForgeTextComponentFactory.INSTANCE;
    }

    @Override
    public TagFactory getTagFactory() {
        return ForgeTagFactory.INSTANCE;
    }

    @Override
    public IItemStack createItemFromTag(ICompoundTag data) {
        return IItemStack.class.cast(ItemStack.of((CompoundNBT) data));
    }

    @Override
    public IItemStack createItemFromId(IIdentifier id) {
        return IItemStack.class.cast(new ItemStack(net.minecraftforge.registries.ForgeRegistries.ITEMS.getValue((ResourceLocation) id)));
    }

    @Override
    public Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Command<CommandSource> createCommand(Function<IPlayer, Integer> command) {
        return ctx -> command.apply((IPlayer) ctx.getSource().getPlayerOrException());
    }

    @Override
    public RegistryHandler getRegistryHandler() {
        return ForgeRegistryHandler.INSTANCE;
    }

    @Override
    public String translate(String translationKey) {
        return LanguageMap.getInstance().getOrDefault(translationKey);
    }

    @Override
    public IIdentifier createIdentifier(String namespace, String path) {
        return (IIdentifier) new ResourceLocation(namespace, path);
    }

    @Override
    public IIdentifier createIdentifier(String id) {
        return (IIdentifier) new ResourceLocation(id);
    }

    @Override
    public IIdentifier parseIdentifier(String id) {
        return (IIdentifier) ResourceLocation.tryParse(id);
    }
}
