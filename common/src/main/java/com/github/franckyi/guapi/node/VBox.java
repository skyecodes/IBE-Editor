package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.util.Align;

public class VBox extends Group {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align.Horizontal> alignmentProperty = PropertyFactory.ofObject(Align.Horizontal.LEFT);

    public VBox() {
        this(0);
    }

    public VBox(int spacing) {
        setSpacing(spacing);
        spacingProperty().addListener(this::shouldComputeSize);
        alignmentProperty().addListener(event -> updateChildrenPos());
    }

    public int getSpacing() {
        return spacingProperty().getValue();
    }

    public IntegerProperty spacingProperty() {
        return spacingProperty;
    }

    public void setSpacing(int value) {
        spacingProperty().setValue(value);
    }

    public Align.Horizontal getAlignment() {
        return alignmentProperty().getValue();
    }

    public ObjectProperty<Align.Horizontal> alignmentProperty() {
        return alignmentProperty;
    }

    public void setAlignment(Align.Horizontal value) {
        alignmentProperty().setValue(value);
    }

    @Override
    public void updateChildrenPos() {
        int y = getY() + getPadding().getTop();
        for (Node child : getChildren()) {
            int x = Align.getAlignedX(getAlignment(), this, child.getWidth());
            child.setX(x);
            child.setY(y);
            y += child.getHeight() + getSpacing();
        }
    }

    @Override
    public String toString() {
        return "VBox{" +
                "children=" + children +
                '}';
    }
}
