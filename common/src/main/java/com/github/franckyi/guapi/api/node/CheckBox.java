package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface CheckBox extends Labeled {
    boolean isChecked();

    BooleanProperty checkedProperty();

    void setChecked(boolean value);
}
