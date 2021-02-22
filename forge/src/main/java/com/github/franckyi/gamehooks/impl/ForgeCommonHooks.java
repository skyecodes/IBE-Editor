package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.ItemFactory;
import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.api.common.TextFactory;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.impl.common.ForgeItemFactory;
import com.github.franckyi.gamehooks.impl.common.ForgeNetwork;
import com.github.franckyi.gamehooks.impl.common.ForgeTagFactory;
import com.github.franckyi.gamehooks.impl.common.ForgeTextFactory;

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
    public Network network() {
        return ForgeNetwork.INSTANCE;
    }
}
