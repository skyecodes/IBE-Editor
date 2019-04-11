package com.github.franckyi.ibeeditor.proxy;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public interface IProxy {
    default void onSetup() {
    }

    default void onServerStarting(FMLServerStartingEvent event) {
    }
}
