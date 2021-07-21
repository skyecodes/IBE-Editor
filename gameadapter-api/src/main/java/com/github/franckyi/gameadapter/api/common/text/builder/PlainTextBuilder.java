package com.github.franckyi.gameadapter.api.common.text.builder;

import com.github.franckyi.gameadapter.api.common.text.PlainText;

public interface PlainTextBuilder extends PlainText, GenericTextBuilder<PlainTextBuilder> {
    default PlainTextBuilder text(String text) {
        return with(t -> t.setText(text));
    }
}
