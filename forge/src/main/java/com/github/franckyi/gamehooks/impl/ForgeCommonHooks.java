package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
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
        return ForgeItem::new;
    }

    @Override
    public BlockFactory block() {
        return ForgeBlock::new;
    }

    @Override
    public EntityFactory entity() {
        return ForgeEntity::new;
    }

    @Override
    public Network<?> network() {
        return ForgeNetwork.INSTANCE;
    }
}
