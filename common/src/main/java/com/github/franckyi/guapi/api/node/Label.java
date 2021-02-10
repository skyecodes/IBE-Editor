package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface Label extends Labeled {
    Align getTextAlign();

    ObjectProperty<Align> textAlignProperty();

    void setTextAlign(Align value);

    boolean hasShadow();

    BooleanProperty shadowProperty();

    void setShadow(boolean value);
}
