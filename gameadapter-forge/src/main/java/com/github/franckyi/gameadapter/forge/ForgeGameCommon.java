package com.github.franckyi.gameadapter.forge;

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
import com.github.franckyi.gameadapter.forge.common.ForgeRegistryHandler;
import com.github.franckyi.gameadapter.forge.common.ForgeTagFactory;
import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public final class ForgeGameCommon implements GameCommon {
    public static final GameCommon INSTANCE = new ForgeGameCommon();

    private ForgeGameCommon() {
    }

    @Override
    public TagFactory getTagFactory() {
        return ForgeTagFactory.INSTANCE;
    }

    @Override
    public IItemStack createItemFromTag(ICompoundTag data) {
        return IItemStack.class.cast(ItemStack.of((CompoundTag) data));
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
    public Command<CommandSourceStack> createCommand(Function<IPlayer, Integer> command) {
        return ctx -> command.apply((IPlayer) ctx.getSource().getPlayerOrException());
    }

    @Override
    public RegistryHandler getRegistryHandler() {
        return ForgeRegistryHandler.INSTANCE;
    }

    @Override
    public String translate(String translationKey) {
        return Language.getInstance().getOrDefault(translationKey);
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

    @Override
    public IPlainText createPlainText(String text) {
        return (IPlainText) new TextComponent(text);
    }

    @Override
    public ITranslatedText createTranslatedText(String key) {
        return (ITranslatedText) new TranslatableComponent(key);
    }

    @Override
    public ITranslatedText createTranslatedText(String key, Object... args) {
        return (ITranslatedText) new TranslatableComponent(key, args);
    }

    @Override
    public ITextEvent createTextClickEvent(String action, String value) {
        return (ITextEvent) new ClickEvent(ClickEvent.Action.getByName(action), value);
    }

    @Override
    public IText createTextFromJson(String json) {
        return (IText) Component.Serializer.fromJson(json);
    }

    @Override
    public IText getEmptyText() {
        return (IText) TextComponent.EMPTY;
    }

    @Override
    public boolean isClient() {
        return FMLLoader.getDist().isClient();
    }
}
