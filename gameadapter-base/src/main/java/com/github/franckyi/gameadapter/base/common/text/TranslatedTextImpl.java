package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.api.common.text.TranslatedText;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TranslatedTextImpl extends AbstractTranslatedText implements TranslatedTextBuilder {
    public TranslatedTextImpl() {
    }

    public TranslatedTextImpl(String translate) {
        super(translate);
    }

    public TranslatedTextImpl(TranslatedText text) {
        super(text);
    }

    public static class Serializer implements JsonSerializer<TranslatedTextImpl> {
        @Override
        public JsonElement serialize(TranslatedTextImpl src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = context.serialize(new TranslatedTextImplWrapper(src)).getAsJsonObject();
            if (json.entrySet().size() == 1 && json.has("extra")) {
                return json.get("extra");
            } else if (!json.has("translate")) {
                json.addProperty("translate", "");
            }
            return json;
        }

        private static class TranslatedTextImplWrapper extends AbstractTranslatedText {
            private TranslatedTextImplWrapper(TranslatedText text) {
                super(text);
            }
        }
    }
}
