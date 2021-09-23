package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.builder.CheckBoxBuilder;
import com.github.franckyi.guapi.api.util.NodeType;
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
    protected NodeType<?> getType() {
        return NodeType.CHECK_BOX;
    }

    @Override
    public String toString() {
        return "CheckBox{\"" + getLabel() + "\"}";
    }
}
