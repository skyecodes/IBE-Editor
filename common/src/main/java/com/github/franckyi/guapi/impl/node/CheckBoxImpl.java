package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.guapi.api.node.builder.CheckBoxBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class CheckBoxImpl extends AbstractCheckBox implements CheckBoxBuilder {
    public CheckBoxImpl() {
    }

    public CheckBoxImpl(String label) {
        super(label);
    }

    public CheckBoxImpl(Text label) {
        super(label);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.CHECKBOX;
    }

    @Override
    public String toString() {
        return "CheckBox{\"" + getLabel() + "\"}";
    }
}
