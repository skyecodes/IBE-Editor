package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.databindings.DataBindingsImpl;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.base.NodeFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommonInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - common");
        DataBindingsImpl.init();
        Game.setDefaultLogger(LogManager.getLogger("IBE Editor"));
        Guapi.setNodeFactory(NodeFactoryImpl.INSTANCE);
        ModNetwork.init();
        CommonConfiguration.load();
    }
}