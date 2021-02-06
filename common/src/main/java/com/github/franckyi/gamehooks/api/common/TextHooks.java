package com.github.franckyi.gamehooks.api.common;

public interface TextHooks<T> {
    T getLiteralText(String text);

    T getTranslatableText(String text);

    default T getText(String text, boolean translated) {
        return translated ? getTranslatableText(text) : getLiteralText(text);
    }
}
