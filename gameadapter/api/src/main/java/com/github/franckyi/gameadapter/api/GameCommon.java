package com.github.franckyi.gameadapter.api;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface GameCommon {
    <T> TextComponentFactory<T> getTextComponentFactory();

    TagFactory getTagFactory();

    IItemStack createItemFromTag(ICompoundTag data);

    IItemStack createItemFromId(IIdentifier id);

    Path getGameDir();

    Command<?> createCommand(Function<IPlayer, Integer> command);

    RegistryHandler getRegistryHandler();

    String translate(String translationKey);

    IIdentifier createIdentifier(String namespace, String path);

    IIdentifier createIdentifier(String id);

    IIdentifier parseIdentifier(String id);
}
