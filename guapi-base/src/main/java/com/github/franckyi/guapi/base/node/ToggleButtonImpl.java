package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.builder.ToggleButtonBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

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
