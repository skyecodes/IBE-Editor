package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.TextEvent;
import com.github.franckyi.gameadapter.api.common.text.TextFactory;
import com.github.franckyi.gameadapter.api.common.text.builder.PlainTextBuilder;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;

public final class TextFactoryImpl implements TextFactory {
    public static final TextFactory INSTANCE = new TextFactoryImpl();
    private static final Text EMPTY_TEXT = INSTANCE.createPlainText();

    private TextFactoryImpl() {
    }

    @Override
    public Text createEmptyText() {
        return EMPTY_TEXT;
    }

    @Override
    public PlainTextBuilder createPlainText() {
        return new PlainTextImpl();
    }

    @Override
    public PlainTextBuilder createPlainText(String text) {
        return new PlainTextImpl(text);
    }

    @Override
    public TranslatedTextBuilder createTranslatedText() {
        return new TranslatedTextImpl();
    }

    @Override
    public TranslatedTextBuilder createTranslatedText(String translate) {
        return new TranslatedTextImpl(translate);
    }

    @Override
    public TextEvent createEvent(String action, String value) {
        return new TextEventImpl(action, value);
    }
}
