package com.github.franckyi.guapi;

import com.github.franckyi.guapi.forge.ForgeScreenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("guapi-forge")
public final class ForgeGuapiMod {
    public ForgeGuapiMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        Guapi.setScreenHandler(ForgeScreenHandler.INSTANCE);
    }
}
