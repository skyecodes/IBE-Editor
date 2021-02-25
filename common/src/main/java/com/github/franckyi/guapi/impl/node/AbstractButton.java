package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.Button;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public abstract class AbstractButton extends AbstractLabeled implements Button {
    protected AbstractButton() {
        this(emptyText());
    }

    protected AbstractButton(String text) {
        this(text(text));
    }

    protected AbstractButton(Text label) {
        super(label);
        labelProperty().addListener(this::shouldComputeSize);
    }
}
