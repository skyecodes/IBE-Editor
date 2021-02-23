package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.*;
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
        return FabricItem::new;
    }

    @Override
    public BlockFactory block() {
        return FabricBlock::new;
    }

    @Override
    public EntityFactory entity() {
        return FabricEntity::new;
    }

    @Override
    public Network<?> network() {
        return FabricNetwork.INSTANCE;
    }
}
