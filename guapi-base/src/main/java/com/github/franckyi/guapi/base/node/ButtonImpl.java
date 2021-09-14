package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.builder.ButtonBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class ButtonImpl extends AbstractButton implements ButtonBuilder {
    public ButtonImpl() {
    }

    public ButtonImpl(String text) {
        super(text);
    }

    public ButtonImpl(IText label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.BUTTON;
    }

    @Override
    public String toString() {
        return "Button{\"" + getLabel() + "\"}";
    }
}
