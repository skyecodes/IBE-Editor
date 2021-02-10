package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;

public interface Spaced {
    int getSpacing();

    IntegerProperty spacingProperty();

    void setSpacing(int value);
}
