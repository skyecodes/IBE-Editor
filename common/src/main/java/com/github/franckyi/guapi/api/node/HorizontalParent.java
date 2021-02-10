package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface HorizontalParent {
    Align.Vertical getAlignment();

    ObjectProperty<Align.Vertical> alignmentProperty();

    void setAlignment(Align.Vertical value);
}
