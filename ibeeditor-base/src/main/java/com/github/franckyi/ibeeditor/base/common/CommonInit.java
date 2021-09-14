package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.databindings.base.DataBindingsImpl;
import com.github.franckyi.gameadapter.api.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommonInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - common");
        DataBindingsImpl.init();
        Game.setDefaultLogger(LogManager.getLogger("IBE Editor"));
    }

    public static void setup() {
        LOGGER.info("Setting up IBE Editor - common");
        CommonConfiguration.load();
    }
}
