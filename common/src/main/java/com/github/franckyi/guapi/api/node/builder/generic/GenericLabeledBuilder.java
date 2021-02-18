package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.gamehooks.util.common.text.TextFormatting;
import com.github.franckyi.gamehooks.util.common.text.TextStyle;
import com.github.franckyi.guapi.api.node.Labeled;

public interface GenericLabeledBuilder<N extends Labeled> extends Labeled, GenericControlBuilder<N> {
    default N label(Text value) {
        return with(n -> n.setLabel(value));
    }

    default N label(String text) {
        return label(Text.literal(text));
    }

    default N label(String text, TextStyle style) {
        return label(Text.literal(text, style));
    }

    default N label(String text, TextFormatting... formatting) {
        return label(Text.literal(text, formatting));
    }

    default N translated(String text) {
        return label(Text.translated(text));
    }

    default N translated(String text, TextStyle style) {
        return label(Text.translated(text, style));
    }

    default N translated(String text, TextFormatting... formatting) {
        return label(Text.translated(text, formatting));
    }
}
