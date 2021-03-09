package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.MinecraftCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEEditorCommon {
    public static final Logger LOGGER = LogManager.getLogger("IBEEditor");
    public static final Marker MARKER = MarkerManager.getMarker("Common");

    public static void init(MinecraftCommon common) {
        LOGGER.debug(MARKER, "Initializing IBE Editor - common");
        Minecraft.setCommon(common);
        Minecraft.setLogger(LOGGER);
        IBEEditorNetwork.init();
        IBEEditorConfiguration.loadCommon();
    }
}
