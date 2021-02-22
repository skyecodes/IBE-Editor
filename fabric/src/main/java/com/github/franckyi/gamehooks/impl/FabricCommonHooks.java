package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.FabricItemFactory;
import com.github.franckyi.gamehooks.impl.common.FabricNetwork;
import com.github.franckyi.gamehooks.impl.common.FabricTagFactory;
import com.github.franckyi.gamehooks.impl.common.FabricTextFactory;

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
    public Network network() {
        return FabricNetwork.INSTANCE;
    }
}
