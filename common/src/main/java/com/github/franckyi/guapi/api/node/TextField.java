package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;

public interface TextField extends Labeled {
    String getText();

    StringProperty textProperty();

    void setText(String value);

    int getMaxLength();

    IntegerProperty maxLengthProperty();

    void setMaxLength(int value);
}
