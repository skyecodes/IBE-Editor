package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;

public interface Labeled extends Control {
    default IText getLabel() {
        return labelProperty().getValue();
    }

    ObjectProperty<IText> labelProperty();

    default void setLabel(IText value) {
        labelProperty().setValue(value);
    }
}
