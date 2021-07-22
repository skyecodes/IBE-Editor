package com.github.franckyi.gameadapter;

import com.github.franckyi.gameadapter.api.common.text.TextFactory;
import com.google.gson.Gson;

public final class TextHandler {
    private static TextFactory textFactory;
    private static Gson serializer;

    public static TextFactory getTextFactory() {
        return textFactory;
    }

    public static void setTextFactory(TextFactory textFactory) {
        TextHandler.textFactory = textFactory;
    }

    public static Gson getSerializer() {
        return serializer;
    }

    public static void setSerializer(Gson serializer) {
        TextHandler.serializer = serializer;
    }
}
