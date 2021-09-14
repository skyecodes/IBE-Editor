package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.TextArea;

public interface GenericTextAreaBuilder<N extends TextArea> extends TextArea, GenericTextFieldBuilder<N> {
    default N wrapText(boolean value) {
        return with(n -> n.setWrapText(value));
    }

    default N wrapText() {
        return wrapText(true);
    }
}
