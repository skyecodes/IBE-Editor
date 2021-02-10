package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.CheckBox;

public interface GenericCheckBoxBuilder<N extends CheckBox> extends CheckBox, GenericLabeledBuilder<N> {
    default N checked(boolean value) {
        return with(n -> n.setChecked(value));
    }
}
