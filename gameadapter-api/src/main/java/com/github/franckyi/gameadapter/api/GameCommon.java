package com.github.franckyi.gameadapter.api;

import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.network.Network;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.TagFactory;
import com.github.franckyi.gameadapter.api.common.text.TextComponentFactory;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface GameCommon {
    <T> TextComponentFactory<T> getTextComponentFactory();

    TagFactory getTagFactory();

    Item createItem(CompoundTag tag);

    Block createBlock(CompoundTag state, CompoundTag data);

    Entity createEntity(CompoundTag tag);

    Network getNetwork();

    Path getGameDir();

    Command<?> createCommand(Function<Player, Integer> command);

    Registries getRegistries();

    <P> PlayerFactory<P> getPlayerFactory();

    interface PlayerFactory<P> {
        Player createPlayer(P player);
    }
}
