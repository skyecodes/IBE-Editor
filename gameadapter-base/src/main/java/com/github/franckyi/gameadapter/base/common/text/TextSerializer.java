package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextEvent;
import com.google.gson.*;

import java.lang.reflect.Type;

public final class TextSerializer implements JsonDeserializer<Text> {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Text.class, new TextSerializer())
            .registerTypeAdapter(PlainTextImpl.class, new PlainTextImpl.Serializer())
            .registerTypeAdapter(TranslatedTextImpl.class, new TranslatedTextImpl.Serializer())
            .registerTypeAdapter(TextEvent.class, new TextEventImpl.Serializer())
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
