package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.BlockFactory;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.*;

public final class ForgeCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new ForgeCommonHooks();

    private ForgeCommonHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextFactory<?> text() {
        return ForgeTextFactory.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TagFactory<?> tag() {
        return ForgeTagFactory.INSTANCE;
    }

    @Override
    public ItemFactory item() {
        return ForgeItemFactory.INSTANCE;
    }

    @Override
    public BlockFactory block() {
        return ForgeBlockFactory.INSTANCE;
    }

    @Override
    public Network<?> network() {
        return ForgeNetwork.INSTANCE;
    }
}
