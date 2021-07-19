package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface Label extends Labeled {
    default Align getTextAlign() {
        return textAlignProperty().getValue();
    }

    ObjectProperty<Align> textAlignProperty();

    default void setTextAlign(Align value) {
        textAlignProperty().setValue(value);
    }

    default boolean hasShadow() {
        return shadowProperty().getValue();
    }

    BooleanProperty shadowProperty();

    default void setShadow(boolean value) {
        shadowProperty().setValue(value);
    }
}
