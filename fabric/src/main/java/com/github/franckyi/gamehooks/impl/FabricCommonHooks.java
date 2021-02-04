package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.CommonHooks;

public final class FabricCommonHooks implements CommonHooks {
    public static final CommonHooks INSTANCE = new FabricCommonHooks();

    private FabricCommonHooks() {
    }
}
