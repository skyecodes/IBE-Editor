package com.github.franckyi.minecraft.api.common.text;

import com.github.franckyi.minecraft.util.common.TextFormatting;
import com.github.franckyi.minecraft.util.common.TextStyle;

public interface TextFactory {
    default Text text(String text) {
        return create(text, null, false, null, null);
    }

    default Text text(String text, TextStyle style) {
        return create(text, null, false, style, null);
    }

    default Text text(String text, TextFormatting... formatting) {
        return create(text, null, false, null, formatting);
    }

    default Text link(String text, String url) {
        return create(text, url, false, null, null);
    }

    default Text link(String text, String url, TextStyle style) {
        return create(text, url, false, style, null);
    }

    default Text link(String text, String url, TextFormatting... formatting) {
        return create(text, url, false, null, formatting);
    }

    default Text translatedText(String text) {
        return create(text, null, true, null, null);
    }

    default Text translatedText(String text, TextStyle style) {
        return create(text, null, true, style, null);
    }

    default Text translatedText(String text, TextFormatting... formatting) {
        return create(text, null, true, null, formatting);
    }

    default Text translatedLink(String text, String url) {
        return create(text, url, true, null, null);
    }

    default Text translatedLink(String text, String url, TextStyle style) {
        return create(text, url, true, style, null);
    }

    default Text translatedLink(String text, String url, TextFormatting... formatting) {
        return create(text, url, true, null, formatting);
    }

    Text create(String text, String url, boolean translated, TextStyle style, TextFormatting[] formatting);

    Text empty();
}
