package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.builder.ButtonBuilder;
import net.minecraft.network.chat.Component;

public class ButtonImpl extends AbstractButton implements ButtonBuilder {
    public ButtonImpl() {
    }

    public ButtonImpl(String text) {
        super(text);
    }

    public ButtonImpl(Component label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return Button.class;
    }

    @Override
    public String toString() {
        return "Button{\"" + getLabel() + "\"}";
    }
}
