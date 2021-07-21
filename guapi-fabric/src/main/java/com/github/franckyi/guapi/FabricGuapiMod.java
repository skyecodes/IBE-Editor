package com.github.franckyi.guapi;

import com.github.franckyi.guapi.fabric.FabricScreenHandler;
import net.fabricmc.api.ClientModInitializer;

public final class FabricGuapiMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Guapi.setScreenHandler(FabricScreenHandler.INSTANCE);
    }
}
