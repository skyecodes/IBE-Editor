package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.guapi.api.node.Button;

public abstract class AbstractButton extends AbstractLabeled implements Button {
    protected AbstractButton() {
        this(Text.EMPTY);
    }

    protected AbstractButton(String text) {
        this(Text.literal(text));
    }

    protected AbstractButton(Text label) {
        super(label);
        labelProperty().addListener(this::shouldComputeSize);
    }
}
