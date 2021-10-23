package com.github.franckyi.gameadapter.fabric;

import com.github.franckyi.gameadapter.api.Game;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public final class FabricGameAdapterMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        Game.setCommon(FabricGameCommon.INSTANCE);
    }

    @Override
    public void onInitializeClient() {
        Game.setClient(FabricGameClient.INSTANCE);
    }
}