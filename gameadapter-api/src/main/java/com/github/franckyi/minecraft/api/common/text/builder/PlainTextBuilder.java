package com.github.franckyi.minecraft.api.common.text.builder;

import com.github.franckyi.minecraft.api.common.text.PlainText;

public interface PlainTextBuilder extends PlainText, TextBuilder<PlainTextBuilder> {
    default PlainTextBuilder text(String text) {
        return with(t -> t.setText(text));
    }
}
