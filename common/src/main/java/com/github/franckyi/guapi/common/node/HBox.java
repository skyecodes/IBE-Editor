package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.data.IntegerProperty;
import com.github.franckyi.guapi.common.data.SimpleIntegerProperty;
import com.github.franckyi.guapi.common.data.SimpleObjectProperty;
import com.github.franckyi.guapi.common.data.ObjectProperty;
import com.github.franckyi.guapi.common.theme.Skin;
import com.github.franckyi.guapi.common.theme.Theme;
import com.github.franckyi.guapi.common.util.Align;

import java.util.function.Function;

public class HBox extends Group {
    private final IntegerProperty spacingProperty = new SimpleIntegerProperty();
    private final ObjectProperty<Align.Vertical> alignmentProperty = new SimpleObjectProperty<>(Align.Vertical.TOP);

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

    public void setSpacing(int value) {
        spacingProperty().setValue(value);
    }

    public IntegerProperty spacingProperty() {
        return spacingProperty;
    }

    public Align.Vertical getAlignment() {
        return alignmentProperty().getValue();
    }

    public void setAlignment(Align.Vertical value) {
        alignmentProperty().setValue(value);
    }

    public ObjectProperty<Align.Vertical> alignmentProperty() {
        return alignmentProperty;
    }

    @Override
    protected Function<Theme, Skin<HBox>> getSkinFactory() {
        return Theme::getHBoxSkin;
    }

    @Override
    protected void updateChildrenPos() {
        int x = getX() + getPadding().getLeft();
        for (Node child : getChildren()) {
            int y;
            switch (getAlignment()) {
                case CENTER:
                    y = getY() + getPadding().getTop() + ((getHeight() - getPadding().getVertical()) - child.getHeight()) / 2;
                    break;
                case BOTTOM:
                    y = getY() + getPadding().getTop() + (getHeight() - getPadding().getVertical()) - child.getHeight();
                    break;
                case TOP:
                default:
                    y = getY() + getPadding().getTop();
                    break;
            }
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
