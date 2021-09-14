package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.builder.CheckBoxBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class CheckBoxImpl extends AbstractCheckBox implements CheckBoxBuilder {
    public CheckBoxImpl() {
    }

    public CheckBoxImpl(String label) {
        super(label);
    }

    public CheckBoxImpl(IText label) {
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
