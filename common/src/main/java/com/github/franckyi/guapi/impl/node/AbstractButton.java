package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.minecraft.api.common.text.Text;

import static com.github.franckyi.guapi.GUAPIHelper.*;

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
