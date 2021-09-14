package com.github.franckyi.gameadapter.forge;

import com.github.franckyi.gameadapter.api.Game;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("gameadapter")
public final class ForgeGameAdapterMod {
    public ForgeGameAdapterMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);
    }

    private void onCommonInit(FMLCommonSetupEvent event) {
        Game.setCommon(ForgeGameCommon.INSTANCE);
    }

    private void onClientInit(FMLClientSetupEvent event) {
        Game.setClient(ForgeGameClient.INSTANCE);
    }
}
