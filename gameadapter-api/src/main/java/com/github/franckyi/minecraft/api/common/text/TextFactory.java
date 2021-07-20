package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.minecraft.api.common.text.builder.TranslatedTextBuilder;

public interface TextFactory {
    Text createEmptyText();

    PlainTextBuilder createPlainText();

    PlainTextBuilder createPlainText(String text);

    TranslatedTextBuilder createTranslatedText();

    TranslatedTextBuilder createTranslatedText(String translate);

    TextEvent createEvent(String action, String value);

    default TextEvent createLink(String url) {
        return createEvent("open_url", url);
    }
}
