package com.github.franckyi.ibeeditor.impl;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.CommonHooks;
import com.github.franckyi.gamehooks.api.ServerHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.GUAPIFactory;
import com.github.franckyi.guapi.api.ScreenHandler;
import com.github.franckyi.ibeeditor.api.client.mvc.view.CategoryView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;
import com.github.franckyi.ibeeditor.impl.client.IBEEditorClient;
import com.github.franckyi.ibeeditor.impl.client.mvc.CategoryMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.EditorMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.IntegerEntryMVCImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.StringEntryMVCImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public final class IBEEditor {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final Marker marker = MarkerManager.getMarker("ModInit");

    public static void initCommon(CommonHooks commonHooks) {
        LOGGER.info(marker, "Initializing IBE Editor - common");
        GameHooks.initCommon(commonHooks, LOGGER);
    }

    public static void initClient(ClientHooks clientHooks, ScreenHandler screenHandler) {
        LOGGER.info(marker, "Initializing IBE Editor - client");
        GameHooks.initClient(clientHooks);
        GUAPI.init(screenHandler);
        GUAPIFactory.registerMVC(EditorView.class, EditorMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(CategoryView.class, CategoryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(StringEntryView.class, StringEntryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(IntegerEntryView.class, IntegerEntryMVCImpl.INSTANCE);
        IBEEditorClient.init();
    }

    public static void initServer(ServerHooks serverHooks) {
        LOGGER.info(marker, "Initializing IBE Editor - server");
        GameHooks.initServer(serverHooks);
    }
}
