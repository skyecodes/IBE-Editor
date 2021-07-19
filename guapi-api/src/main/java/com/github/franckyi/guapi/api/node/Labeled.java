package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface Labeled extends Control {
    default Text getLabel() {
        return labelProperty().getValue();
    }

    ObjectProperty<Text> labelProperty();

    default void setLabel(Text value) {
        labelProperty().setValue(value);
    }
}
