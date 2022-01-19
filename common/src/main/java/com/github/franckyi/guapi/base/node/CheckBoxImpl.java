package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.node.builder.CheckBoxBuilder;
import net.minecraft.network.chat.Component;

public class CheckBoxImpl extends AbstractCheckBox implements CheckBoxBuilder {
    public CheckBoxImpl() {
    }

    public CheckBoxImpl(String label) {
        super(label);
    }

    public CheckBoxImpl(Component label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return CheckBox.class;
    }

    @Override
    public String toString() {
        return "CheckBox{\"" + getLabel() + "\"}";
    }
}
