package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface CommonHooks {
    TextFactory textFactory();

    TagFactory tagFactory();

    Item createItem(CompoundTag tag);

    Block createBlock(CompoundTag state, CompoundTag data);

    Entity createEntity(CompoundTag tag);

    Network getNetwork();

    Path getGameDir();

    Command<?> command(Function<Player, Integer> command);
}
