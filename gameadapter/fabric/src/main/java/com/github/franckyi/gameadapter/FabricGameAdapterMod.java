package com.github.franckyi.gameadapter;

import com.github.franckyi.gameadapter.fabric.FabricGameClient;
import com.github.franckyi.gameadapter.fabric.FabricGameCommon;
import com.github.franckyi.gameadapter.fabric.common.FabricRegistries;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public final class FabricGameAdapterMod implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    @Override
    public void onInitialize() {
        Game.setCommon(FabricGameCommon.INSTANCE);
    }

    @Override
    public void onInitializeClient() {
        Game.setClient(FabricGameClient.INSTANCE);
        FabricRegistries.INSTANCE.init();
    }

    @Override
    public void onInitializeServer() {
        FabricRegistries.INSTANCE.init();
    }
}
