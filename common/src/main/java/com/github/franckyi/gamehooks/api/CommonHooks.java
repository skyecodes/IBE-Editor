package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.mojang.brigadier.Command;

import java.nio.file.Path;
import java.util.function.Function;

public interface CommonHooks {
    TextFactory textFactory();

    Item createItemFromTag(ObjectTag tag);

    Block createBlockFromTag(ObjectTag tag);

    Entity createEntityFromTag(ObjectTag tag);

    Network getNetwork();

    Path getGameDir();

    Command<?> command(Function<Player, Integer> command);
}
