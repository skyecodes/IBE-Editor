package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;
import com.github.franckyi.minecraft.impl.common.text.PlainTextImpl;
import com.github.franckyi.minecraft.impl.common.text.TextEventImpl;
import com.github.franckyi.minecraft.impl.common.text.TranslatedTextImpl;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public interface Text {
    List<Text> getExtra();

    void setExtra(List<Text> extra);

    String getColor();

    void setColor(String color);

    Boolean getBold();

    void setBold(Boolean bold);

    Boolean getItalic();

    void setItalic(Boolean italic);

    Boolean getUnderlined();

    void setUnderlined(Boolean underlined);

    Boolean getStrikethrough();

    void setStrikethrough(Boolean strikethrough);

    Boolean getObfuscated();

    void setObfuscated(Boolean obfuscated);

    Event getClickEvent();

    void setClickEvent(Event clickEvent);

    Event getHoverEvent();

    void setHoverEvent(Event hoverEvent);

    <T> T getComponent();

    String getRawText();

    interface Event {
        String OPEN_LINK = "open_url";

        String getAction();

        String getValue();

        static Event createEvent(String action, String value) {
            return new TextEventImpl(action, value);
        }

        static Event createLink(String url) {
            return createEvent(OPEN_LINK, url);
        }
    }

    final class Serializer implements JsonDeserializer<Text> {
        private static final Gson GSON = new Gson();

        @Override
        public Text deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonObject().has("text") ? GSON.fromJson(json, PlainTextImpl.class) : GSON.fromJson(json, TranslatedTextImpl.class);
        }
    }

    Text EMPTY = createPlainText("");

    static PlainTextBuilder createPlainText(String text) {
        return new PlainTextImpl(text);
    }

    static TranslatedTextBuilder createTranslatedText(String translate) {
        return new TranslatedTextImpl(translate);
    }
}
