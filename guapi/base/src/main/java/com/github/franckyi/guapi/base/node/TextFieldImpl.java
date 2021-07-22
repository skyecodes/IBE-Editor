package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.builder.TextFieldBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class TextFieldImpl extends AbstractTextField implements TextFieldBuilder {
    public TextFieldImpl() {
    }

    public TextFieldImpl(String value) {
        super(value);
    }

    public TextFieldImpl(String label, String value) {
        super(label, value);
    }

    public TextFieldImpl(Text label, String value) {
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
