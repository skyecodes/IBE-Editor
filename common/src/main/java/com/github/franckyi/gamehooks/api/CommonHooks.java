package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.api.common.network.Network;

import java.nio.file.Path;

public interface CommonHooks {
    <T> TextFactory<T> text();

    <T> TagFactory<T> tag();

    ItemFactory item();

    BlockFactory block();

    EntityFactory entity();

    Network<?> network();

    Path gameDir();
}
