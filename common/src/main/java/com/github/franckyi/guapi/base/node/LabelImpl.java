package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.builder.LabelBuilder;
import net.minecraft.network.chat.Component;

public class LabelImpl extends AbstractLabel implements LabelBuilder {
    public LabelImpl() {
    }

    public LabelImpl(String text) {
        super(text);
    }

    public LabelImpl(Component text) {
        super(text);
    }

    public LabelImpl(String text, boolean shadow) {
        super(text, shadow);
    }

    public LabelImpl(Component label, boolean shadow) {
        super(label, shadow);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return Label.class;
    }

    @Override
    public String toString() {
        return "Label{\"" + getLabel() + "\"}";
    }
}
