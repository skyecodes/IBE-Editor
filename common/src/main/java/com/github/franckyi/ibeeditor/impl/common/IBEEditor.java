package com.github.franckyi.ibeeditor.impl.common;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.GUAPIFactory;
import com.github.franckyi.guapi.api.ScreenHandler;
import com.github.franckyi.ibeeditor.api.client.mvc.view.*;
import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import com.github.franckyi.ibeeditor.impl.client.mvc.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEEditor {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final Marker MARKER = MarkerManager.getMarker("ModInit");

    public static void initCommon(CommonHooks commonHooks) {
        LOGGER.info(MARKER, "Initializing IBE Editor - common");
        GameHooks.initCommon(commonHooks, LOGGER);
        IBEEditorNetwork.init();
        IBEEditorConfiguration.load();
    }

    public static void initClient(ClientHooks clientHooks, ScreenHandler screenHandler) {
        LOGGER.info(MARKER, "Initializing IBE Editor - client");
        GameHooks.initClient(clientHooks);
        GUAPI.init(screenHandler);
        GUAPIFactory.registerMVC(EditorView.class, EditorMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(CategoryView.class, CategoryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(StringEntryView.class, StringEntryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(IntegerEntryView.class, IntegerEntryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(NBTEditorView.class, NBTEditorMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(TagView.class, TagMVCImpl.INSTANCE);
        IBEEditorClient.init();
    }
}
