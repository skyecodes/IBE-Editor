package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface CheckBox extends Labeled {
    default boolean isChecked() {
        return checkedProperty().getValue();
    }

    BooleanProperty checkedProperty();

    default void setChecked(boolean value) {
        checkedProperty().setValue(value);
    }
}
