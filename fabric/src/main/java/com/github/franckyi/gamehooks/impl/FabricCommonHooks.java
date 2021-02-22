package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.BlockFactory;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;

public final class FabricCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new FabricCommonHooks();

    private FabricCommonHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextFactory<?> text() {
        return FabricTextFactory.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TagFactory<?> tag() {
        return FabricTagFactory.INSTANCE;
    }

    @Override
    public ItemFactory item() {
        return FabricItemFactory.INSTANCE;
    }

    @Override
    public BlockFactory block() {
        return FabricBlockFactory.INSTANCE;
    }

    @Override
    public Network<?> network() {
        return FabricNetwork.INSTANCE;
    }
}
