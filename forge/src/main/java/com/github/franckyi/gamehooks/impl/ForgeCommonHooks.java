package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.TextFactory;
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
}
