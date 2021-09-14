package com.github.franckyi.guapi.fabric;

import com.github.franckyi.guapi.api.Guapi;
import net.fabricmc.api.ClientModInitializer;

public final class FabricGuapiMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Guapi.setScreenHandler(FabricScreenHandler.INSTANCE);
    }
}
