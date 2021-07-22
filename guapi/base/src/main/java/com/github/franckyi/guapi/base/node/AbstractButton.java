package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.Button;

import static com.github.franckyi.guapi.GuapiHelper.*;

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
