package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.util.common.text.Text;

public interface Labeled extends Control {
    default Text getLabel() {
        return labelProperty().getValue();
    }

    ObjectProperty<Text> labelProperty();

    default void setLabel(Text value) {
        labelProperty().setValue(value);
    }

    default <T> T getLabelComponent() {
        return this.<T>labelComponentProperty().getValue();
    }

    <T> ObjectProperty<T> labelComponentProperty();

    default <T> void setLabelComponent(T value) {
        labelComponentProperty().setValue(value);
    }
}
