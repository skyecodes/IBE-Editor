package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.common.TextHooks;
import com.github.franckyi.gamehooks.impl.common.ForgeTextHooks;

public final class ForgeCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new ForgeCommonHooks();

    private ForgeCommonHooks() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextHooks<?> text() {
        return ForgeTextHooks.INSTANCE;
    }
}
