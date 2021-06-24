package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.builder.ToggleButtonBuilder;
import com.github.franckyi.guapi.util.NodeType;
import com.github.franckyi.minecraft.api.common.text.Text;

public class ToggleButtonImpl extends AbstractToggleButton implements ToggleButtonBuilder {
    public ToggleButtonImpl() {
    }

    public ToggleButtonImpl(String text) {
        super(text);
    }

    public ToggleButtonImpl(Text label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TOGGLE_BUTTON;
    }
}
