package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.guapi.theme.Skin;
import com.github.franckyi.guapi.theme.Theme;
import com.github.franckyi.guapi.util.Align;

import java.util.function.Function;

public class VBox extends Group {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align.Horizontal> alignmentProperty = PropertyFactory.ofObject(Align.Horizontal.LEFT);

    public VBox() {
        this(0);
    }

    public VBox(int spacing) {
        setSpacing(spacing);
        spacingProperty().addListener(event -> computeHeight());
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
    @SuppressWarnings("unchecked")
    protected Function<Theme, Skin<VBox>> getSkinFactory() {
        return Theme::getVBoxSkin;
    }

    @Override
    protected void updateChildrenPos() {
        int y = getY() + getPadding().getTop();
        for (Node child : getChildren()) {
            int x;
            switch (getAlignment()) {
                case CENTER:
                    x = getX() + getPadding().getLeft() + ((getWidth() - getPadding().getHorizontal()) - child.getWidth()) / 2;
                    break;
                case RIGHT:
                    x = getX() + getPadding().getLeft() + (getWidth() - getPadding().getHorizontal()) - child.getWidth();
                    break;
                case LEFT:
                default:
                    x = getX() + getPadding().getLeft();
                    break;
            }
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
