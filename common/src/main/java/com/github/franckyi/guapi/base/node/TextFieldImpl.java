package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.builder.TextFieldBuilder;
import com.github.franckyi.guapi.api.util.NodeType;
import net.minecraft.network.chat.Component;

public class TextFieldImpl extends AbstractTextField implements TextFieldBuilder {
    public TextFieldImpl() {
    }

    public TextFieldImpl(String value) {
        super(value);
    }

    public TextFieldImpl(String label, String value) {
        super(label, value);
    }

    public TextFieldImpl(Component label, String value) {
        super(label, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TEXT_FIELD;
    }

    @Override
    public String toString() {
        return "TextField{\"" + getText() + "\"}";
    }
}
