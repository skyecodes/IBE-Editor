package com.github.franckyi.gameadapter;

import com.github.franckyi.gameadapter.fabric.FabricGameClient;
import com.github.franckyi.gameadapter.fabric.FabricGameCommon;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public final class FabricGameAdapterMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Game.setClient(FabricGameClient.INSTANCE);
    }

    @Override
    public void onInitialize() {
        Game.setCommon(FabricGameCommon.INSTANCE);
    }
}
