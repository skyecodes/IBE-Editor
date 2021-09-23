package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.Button;
import net.minecraft.network.chat.Component;

import static com.github.franckyi.guapi.api.GuapiHelper.EMPTY_TEXT;
import static com.github.franckyi.guapi.api.GuapiHelper.text;

public abstract class AbstractButton extends AbstractLabeled implements Button {
    protected AbstractButton() {
        this(EMPTY_TEXT);
    }

    protected AbstractButton(String text) {
        this(text(text));
    }

    protected AbstractButton(Component label) {
        super(label);
        labelProperty().addListener(this::shouldComputeSize);
    }
}
