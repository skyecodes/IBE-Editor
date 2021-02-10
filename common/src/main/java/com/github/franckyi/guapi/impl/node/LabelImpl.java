package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.builder.LabelBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class LabelImpl extends AbstractLabel implements LabelBuilder {
    public LabelImpl() {
    }

    public LabelImpl(String text) {
        super(text);
    }

    public LabelImpl(Text label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.LABEL;
    }
}
