package com.github.franckyi.ibeeditor.base.common;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;

import static com.github.franckyi.guapi.GuapiHelper.*;

public final class Messages {
    public static TranslatedTextBuilder withPrefix(Text text) {
        return translated("ibeeditor.message.prefix").extra(text);
    }
}
