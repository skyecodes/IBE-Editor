package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface HorizontalParent {
    boolean isFillHeight();

    BooleanProperty fillHeightProperty();

    void setFillHeight(boolean value);
}
