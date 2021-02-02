package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.data.SimpleObjectProperty;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.theme.Skin;
import com.github.franckyi.guapi.common.theme.Theme;
import com.github.franckyi.guapi.common.util.Align;

import java.util.function.Function;

public class VBox extends Group {
    private final ObjectProperty<Integer> spacingProperty = new SimpleObjectProperty<>(0);
    private final ObjectProperty<Align.Horizontal> alignmentProperty = new SimpleObjectProperty<>(Align.Horizontal.LEFT);

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

    public void setSpacing(int value) {
        spacingProperty().setValue(value);
    }

    public ObjectProperty<Integer> spacingProperty() {
        return spacingProperty;
    }

    public Align.Horizontal getAlignment() {
        return alignmentProperty().getValue();
    }

    public void setAlignment(Align.Horizontal value) {
        alignmentProperty().setValue(value);
    }

    public ObjectProperty<Align.Horizontal> alignmentProperty() {
        return alignmentProperty;
    }

    @Override
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
