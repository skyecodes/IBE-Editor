package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.common.BlockFactory;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.api.common.network.Network;

public interface CommonHooks {
    <T> TextFactory<T> text();

    <T> TagFactory<T> tag();

    ItemFactory item();

    BlockFactory block();

    Network<?> network();
}
