package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.util.Align;

public interface Box extends Group {
    int getSpacing();

    IntegerProperty spacingProperty();

    void setSpacing(int value);

    Align getAlignment();

    ObjectProperty<Align> alignmentProperty();

    void setAlignment(Align value);

    void setWeight(Node node, int value);

    void resetWeight(Node node);

    int getWeight(Node node);
}
