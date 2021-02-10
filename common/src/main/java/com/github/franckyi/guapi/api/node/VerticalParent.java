package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface VerticalParent {
    Align.Horizontal getAlignment();

    ObjectProperty<Align.Horizontal> alignmentProperty();

    void setAlignment(Align.Horizontal value);
}
