package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.impl.common.text.PlainTextImpl;
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

    interface Event {
        String getAction();

        String getValue();
    }

    // TODO rework serializer so it doesn't depend on implementations
    final class Serializer implements JsonDeserializer<Text> {
        private static final Gson GSON = new Gson();
        @Override
        public Text deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonObject().has("text") ? GSON.fromJson(json, PlainTextImpl.class) : GSON.fromJson(json, TranslatedTextImpl.class);
        }
    }
}
