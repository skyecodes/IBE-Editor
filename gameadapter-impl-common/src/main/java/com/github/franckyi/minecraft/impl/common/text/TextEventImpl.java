package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.TextEvent;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Objects;

public class TextEventImpl implements TextEvent {
    private final String action;
    private final String value;

    public TextEventImpl(String action, String value) {
        this.action = action;
        this.value = value;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextEventImpl event = (TextEventImpl) o;

        if (!Objects.equals(action, event.action)) return false;
        return Objects.equals(value, event.value);
    }

    @Override
    public int hashCode() {
        int result = action != null ? action.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public static final class Serializer implements JsonSerializer<TextEvent>, JsonDeserializer<TextEvent> {
        @Override
        public TextEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return TextSerializer.GSON.fromJson(json, TextEventImpl.class);
        }

        @Override
        public JsonElement serialize(TextEvent src, Type typeOfSrc, JsonSerializationContext context) {
            return TextSerializer.GSON.toJsonTree(src);
        }
    }
}
