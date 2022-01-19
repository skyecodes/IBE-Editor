package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.ToggleButton;
import com.github.franckyi.guapi.api.node.builder.ToggleButtonBuilder;
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
    protected Class<?> getType() {
        return ToggleButton.class;
    }
}
