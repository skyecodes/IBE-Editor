package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.databindings.DefaultDataBindings;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.MinecraftCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommonInit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init(MinecraftCommon common) {
        LOGGER.debug("Initializing IBE Editor - common");
        DefaultDataBindings.init();
        Minecraft.setCommon(common);
        Minecraft.setDefaultLogger(LogManager.getLogger("IBE Editor"));
        Networking.init();
        CommonConfiguration.load();
    }
}
