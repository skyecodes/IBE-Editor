package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.api.common.text.PlainText;
import com.github.franckyi.gameadapter.api.common.text.builder.PlainTextBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PlainTextImpl extends AbstractPlainText implements PlainTextBuilder {
    public PlainTextImpl() {
    }

    public PlainTextImpl(String text) {
        super(text);
    }

    public PlainTextImpl(PlainText text) {
        super(text);
    }

    public static class Serializer implements JsonSerializer<PlainTextImpl> {
        @Override
        public JsonElement serialize(PlainTextImpl src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = context.serialize(new PlainTextImplWrapper(src)).getAsJsonObject();
            if (json.entrySet().size() == 1 && json.has("extra")) {
                return json.get("extra");
            } else if (!json.has("text")) {
                json.addProperty("text", "");
            }
            return json;
        }

        private static class PlainTextImplWrapper extends AbstractPlainText {
            private PlainTextImplWrapper(PlainText text) {
                super(text);
            }
        }
    }
}
