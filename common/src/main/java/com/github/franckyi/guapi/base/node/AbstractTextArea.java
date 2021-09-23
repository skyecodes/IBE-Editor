package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.TextArea;
import net.minecraft.network.chat.Component;

public abstract class AbstractTextArea extends AbstractTextField implements TextArea {
    protected AbstractTextArea() {
    }

    protected AbstractTextArea(String value) {
        super(value);
    }

    protected AbstractTextArea(String label, String value) {
        super(label, value);
    }

    protected AbstractTextArea(Component label, String value) {
        super(label, value);
    }
}
