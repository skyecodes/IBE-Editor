package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.guapi.api.node.builder.TextAreaBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class TextAreaImpl extends AbstractTextArea implements TextAreaBuilder {
    public TextAreaImpl() {
    }

    public TextAreaImpl(String value) {
        super(value);
    }

    public TextAreaImpl(String label, String value) {
        super(label, value);
    }

    public TextAreaImpl(Text label, String value) {
        super(label, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<TextArea> getType() {
        return NodeType.TEXT_AREA;
    }
}
