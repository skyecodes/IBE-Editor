package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import net.minecraft.network.chat.Component;

public interface Labeled extends Control {
    default Component getLabel() {
        return labelProperty().getValue();
    }

    ObjectProperty<Component> labelProperty();

    default void setLabel(Component value) {
        labelProperty().setValue(value);
    }
}
