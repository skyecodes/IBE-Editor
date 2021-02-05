package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.util.Align;

public class HBox extends Group {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align.Vertical> alignmentProperty = PropertyFactory.ofObject(Align.Vertical.TOP);

    public HBox() {
        this(0);
    }

    public HBox(int spacing) {
        setSpacing(spacing);
        spacingProperty().addListener(event -> computeWidth());
        alignmentProperty().addListener(event -> updateChildrenPos());
        computeSize();
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

    public Align.Vertical getAlignment() {
        return alignmentProperty().getValue();
    }

    public ObjectProperty<Align.Vertical> alignmentProperty() {
        return alignmentProperty;
    }

    public void setAlignment(Align.Vertical value) {
        alignmentProperty().setValue(value);
    }

    @Override
    public void updateChildrenPos() {
        int x = getX() + getPadding().getLeft();
        for (Node child : getChildren()) {
            int y = Align.getAlignedY(getAlignment(), this, child.getHeight());
            child.setX(x);
            child.setY(y);
            x += child.getWidth() + getSpacing();
        }
    }

    @Override
    public String toString() {
        return "HBox{" +
                "children=" + children +
                '}';
    }
}
