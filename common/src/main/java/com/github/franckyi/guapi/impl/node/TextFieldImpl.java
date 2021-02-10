package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.builder.TextFieldBuilder;
import com.github.franckyi.guapi.util.NodeType;

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
        return NodeType.TEXTFIELD;
    }
}
