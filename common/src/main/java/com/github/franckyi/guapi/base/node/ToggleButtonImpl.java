package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.builder.ToggleButtonBuilder;
import com.github.franckyi.guapi.api.util.NodeType;
import net.minecraft.network.chat.Component;

public class ToggleButtonImpl extends AbstractToggleButton implements ToggleButtonBuilder {
    public ToggleButtonImpl() {
    }

    public ToggleButtonImpl(String text) {
        super(text);
    }

    public ToggleButtonImpl(Component label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TOGGLE_BUTTON;
    }
}
