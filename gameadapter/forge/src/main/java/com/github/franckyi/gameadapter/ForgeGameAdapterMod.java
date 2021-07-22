package com.github.franckyi.gameadapter;

import com.github.franckyi.gameadapter.forge.ForgeGameClient;
import com.github.franckyi.gameadapter.forge.ForgeGameCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("gameadapter-forge")
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
