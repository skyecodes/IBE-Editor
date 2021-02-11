package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.builder.ButtonBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class ButtonImpl extends AbstractButton implements ButtonBuilder {
    public ButtonImpl() {
    }

    public ButtonImpl(String text) {
        super(text);
    }

    public ButtonImpl(Text label) {
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
