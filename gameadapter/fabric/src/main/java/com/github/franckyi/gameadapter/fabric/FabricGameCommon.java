package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.GameCommon;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.TagFactory;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.common.text.ITranslatedText;
import com.github.franckyi.gameadapter.fabric.common.FabricRegistryHandler;
import com.github.franckyi.gameadapter.fabric.common.FabricTagFactory;
import com.mojang.brigadier.Command;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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

    @Override
    public IPlainText createPlainText(String text) {
        return (IPlainText) new LiteralText(text);
    }

    @Override
    public ITranslatedText createTranslatedText(String key) {
        return (ITranslatedText) new TranslatableText(key);
    }

    @Override
    public ITranslatedText createTranslatedText(String key, Object... args) {
        return (ITranslatedText) new TranslatableText(key, args);
    }

    @Override
    public ITextEvent createTextClickEvent(String action, String value) {
        return (ITextEvent) new ClickEvent(ClickEvent.Action.byName(action), value);
    }

    @Override
    public IText createTextFromJson(String json) {
        return (IText) Text.Serializer.fromJson(json);
    }

    @Override
    public IText getEmptyText() {
        return (IText) LiteralText.EMPTY;
    }
}
