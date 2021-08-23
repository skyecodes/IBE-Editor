package com.github.franckyi.gameadapter.api;

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
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface GameCommon {

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

    IPlainText createPlainText(String text);

    ITranslatedText createTranslatedText(String key);

    ITranslatedText createTranslatedText(String key, Object... args);

    ITextEvent createTextClickEvent(String action, String value);

    IText createTextFromJson(String json);

    IText getEmptyText();
}
