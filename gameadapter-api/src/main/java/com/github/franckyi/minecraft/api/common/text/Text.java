package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface Text {
    List<Text> getExtra();

    void setExtra(List<Text> extra);

    void addExtra(Text extra);

    String getColor();

    void setColor(String color);

    default boolean isBold() {
        return getBold() != null && getBold();
    }

    Boolean getBold();

    void setBold(Boolean bold);

    default boolean isItalic() {
        return getItalic() != null && getItalic();
    }

    Boolean getItalic();

    void setItalic(Boolean italic);

    default boolean isUnderlined() {
        return getUnderlined() != null && getUnderlined();
    }

    Boolean getUnderlined();

    void setUnderlined(Boolean underlined);

    default boolean isStrikethrough() {
        return getStrikethrough() != null && getStrikethrough();
    }

    Boolean getStrikethrough();

    void setStrikethrough(Boolean strikethrough);

    default boolean isObfuscated() {
        return getObfuscated() != null && getObfuscated();
    }

    Boolean getObfuscated();

    void setObfuscated(Boolean obfuscated);

    Event getClickEvent();

    void setClickEvent(Event clickEvent);

    Event getHoverEvent();

    void setHoverEvent(Event hoverEvent);

    <T> T get();

    String getRawText();

    interface Event {
        String OPEN_LINK = "open_url";

        String getAction();

        String getValue();
/*
        static Event createEvent(String action, String value) {
            return new TextEventImpl(action, value);
        }

        static Event createLink(String url) {
            return createEvent(OPEN_LINK, url);
        }

        final class Serializer implements JsonSerializer<Event>, JsonDeserializer<Event> {
            @Override
            public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return Text.Serializer.GSON.fromJson(json, TextEventImpl.class);
            }

            @Override
            public JsonElement serialize(Event src, Type typeOfSrc, JsonSerializationContext context) {
                return Text.Serializer.GSON.toJsonTree(src);
            }
        }
    }

    final class Serializer implements JsonDeserializer<Text> {
        public static final Gson GSON = new GsonBuilder()
                .registerTypeAdapter(Text.class, new Serializer())
                .registerTypeAdapter(PlainTextImpl.class, new PlainTextImpl.Serializer())
                .registerTypeAdapter(TranslatedTextImpl.class, new TranslatedTextImpl.Serializer())
                .registerTypeAdapter(Event.class, new Event.Serializer())
                .create();

        @Override
        public Text deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonArray()) {
                PlainTextImpl text = new PlainTextImpl();
                json.getAsJsonArray().forEach(jsonElement -> text.addExtra(GSON.fromJson(jsonElement, Text.class)));
                return text;
            } else {
                return json.getAsJsonObject().has("text") ? GSON.fromJson(json, PlainTextImpl.class) : GSON.fromJson(json, TranslatedTextImpl.class);
            }
        }
    }

    Text EMPTY = createPlainText("");

    static PlainTextBuilder createPlainText() {
        return new PlainTextImpl();
    }

    static PlainTextBuilder createPlainText(String text) {
        return new PlainTextImpl(text);
    }

    static TranslatedTextBuilder createTranslatedText() {
        return new TranslatedTextImpl();
    }

    static TranslatedTextBuilder createTranslatedText(String translate) {
        return new TranslatedTextImpl(translate);*/
    }

    final class Color {
        public static final String BLACK = "black";
        public static final String DARK_BLUE = "dark_blue";
        public static final String DARK_GREEN = "dark_green";
        public static final String DARK_AQUA = "dark_aqua";
        public static final String DARK_RED = "dark_red";
        public static final String DARK_PURPLE = "dark_purple";
        public static final String GOLD = "gold";
        public static final String GRAY = "gray";
        public static final String DARK_GRAY = "dark_gray";
        public static final String BLUE = "blue";
        public static final String GREEN = "green";
        public static final String AQUA = "aqua";
        public static final String RED = "red";
        public static final String LIGHT_PURPLE = "light_purple";
        public static final String YELLOW = "yellow";
        public static final String WHITE = "white";
    }
}













