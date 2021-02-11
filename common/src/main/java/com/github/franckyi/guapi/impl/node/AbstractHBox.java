package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractHBox extends AbstractSpacedGroup implements HBox {
    private final BooleanProperty fillHeightProperty = PropertyFactory.ofBoolean();

    protected AbstractHBox() {
        this(0);
    }

    protected AbstractHBox(int spacing) {
        this(spacing, Collections.emptyList());
    }

    protected AbstractHBox(Node... children) {
        this(Arrays.asList(children));
    }

    protected AbstractHBox(Collection<? extends Node> children) {
        this(0, children);
    }

    protected AbstractHBox(int spacing, Node... children) {
        this(spacing, Arrays.asList(children));
    }

    protected AbstractHBox(int spacing, Collection<? extends Node> children) {
        super(spacing, children);
        fillHeightProperty().addListener(this::shouldUpdateChildren);
    }

    @Override
    public boolean isFillHeight() {
        return fillHeightProperty().getValue();
    }

    @Override
    public BooleanProperty fillHeightProperty() {
        return fillHeightProperty;
    }

    @Override
    public void setFillHeight(boolean value) {
        fillHeightProperty().setValue(value);
    }

    @Override
    protected void updateChildren() {
        updateChildrenSize();
        int x = Align.getAlignedX(getAlignment().getHorizontalAlign(), this, getComputedWidth() - getPadding().getHorizontal());
        for (Node child : getChildren()) {
            int y = Align.getAlignedY(getAlignment().getVerticalAlign(), this, child.getHeight());
            child.setX(x);
            child.setY(y);
            x += child.getWidth() + getSpacing();
        }
    }

    protected void updateChildrenSize() {
        if (isFillHeight()) {
            getChildren().forEach(node -> node.setParentPrefHeight(getMaxChildrenHeight()));
        } else {
            getChildren().forEach(node -> node.setParentPrefHeight(NONE));
        }
    }
}
