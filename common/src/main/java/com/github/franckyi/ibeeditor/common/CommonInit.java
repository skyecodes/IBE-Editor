package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.databindings.base.DataBindingsImpl;
import com.github.franckyi.ibeeditor.common.network.NetworkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommonInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("Initializing IBE Editor - common");
        DataBindingsImpl.init();
    }

    public static void setup() {
        LOGGER.info("Setting up IBE Editor - common");
        NetworkManager.setup();
        CommonConfiguration.load();
    }
}
