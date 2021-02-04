package com.github.franckyi.gamehooks.impl;

import com.github.franckyi.gamehooks.api.ServerHooks;

public final class ForgeServerHooks implements ServerHooks {
    public static final ForgeServerHooks INSTANCE = new ForgeServerHooks();

    private ForgeServerHooks() {
    }
}
