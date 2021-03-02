package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.CommonHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEEditorCommon {
    public static final Logger LOGGER = LogManager.getLogger("IBEEditor");
    public static final Marker MARKER = MarkerManager.getMarker("Common");

    public static void init(CommonHooks commonHooks) {
        LOGGER.info(MARKER, "Initializing IBE Editor - common");
        GameHooks.initCommon(commonHooks, LOGGER);
        IBEEditorNetwork.init();
        IBEEditorConfiguration.loadClient();
    }
}
