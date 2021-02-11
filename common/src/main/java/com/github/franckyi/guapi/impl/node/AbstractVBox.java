package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractVBox extends AbstractSpacedGroup implements VBox {
    private final BooleanProperty fillWidthProperty = PropertyFactory.ofBoolean();

    protected AbstractVBox() {
        this(0);
    }

    protected AbstractVBox(int spacing) {
        this(spacing, Collections.emptyList());
    }

    protected AbstractVBox(Node... children) {
        this(Arrays.asList(children));
    }

    protected AbstractVBox(Collection<? extends Node> children) {
        this(0, children);
    }

    protected AbstractVBox(int spacing, Node... children) {
        this(spacing, Arrays.asList(children));
    }

    protected AbstractVBox(int spacing, Collection<? extends Node> children) {
        super(spacing, children);
        fillWidthProperty().addListener(this::shouldUpdateChildren);
    }

    @Override
    public boolean isFillWidth() {
        return fillWidthProperty().getValue();
    }

    @Override
    public BooleanProperty fillWidthProperty() {
        return fillWidthProperty;
    }

    @Override
    public void setFillWidth(boolean value) {
        fillWidthProperty().setValue(value);
    }

    @Override
    protected void updateChildren() {
        updateChildrenSize();
        int y = Align.getAlignedY(getAlignment().getVerticalAlign(), this, getComputedHeight() - getPadding().getVertical());
        for (Node child : getChildren()) {
            int x = Align.getAlignedX(getAlignment().getHorizontalAlign(), this, child.getWidth());
            child.setX(x);
            child.setY(y);
            y += child.getHeight() + getSpacing();
        }
    }

    protected void updateChildrenSize() {
        if (isFillWidth()) {
            getChildren().forEach(node -> node.setParentPrefWidth(getMaxChildrenWidth()));
        } else {
            getChildren().forEach(node -> node.setParentPrefWidth(NONE));
        }
    }
}
