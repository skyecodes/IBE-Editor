package com.github.franckyi.guapi.theme;

import com.github.franckyi.guapi.node.Labeled;

public abstract class LabeledSkin<N extends Labeled> extends AbstractSkin<N> {
    @Override
    public int computeWidth(N node) {
        return font().getFontWidth(node.getText());
    }

    @Override
    public int computeHeight(N node) {
        return font().getFontHeight();
    }
}
