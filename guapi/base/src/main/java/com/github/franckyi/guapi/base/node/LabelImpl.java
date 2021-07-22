package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.builder.LabelBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class LabelImpl extends AbstractLabel implements LabelBuilder {
    public LabelImpl() {
    }

    public LabelImpl(String text) {
        super(text);
    }

    public LabelImpl(Text text) {
        super(text);
    }

    public LabelImpl(String text, boolean shadow) {
        super(text, shadow);
    }

    public LabelImpl(Text label, boolean shadow) {
        super(label, shadow);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.LABEL;
    }

    @Override
    public String toString() {
        return "Label{\"" + getLabel() + "\"}";
    }
}
