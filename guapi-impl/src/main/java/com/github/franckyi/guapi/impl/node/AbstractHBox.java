package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractHBox extends AbstractBox implements HBox {
    private final BooleanProperty fillHeightProperty = DataBindings.getPropertyFactory().createBooleanProperty();

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
    public BooleanProperty fillHeightProperty() {
        return fillHeightProperty;
    }

    @Override
    protected void updateChildren() {
        updateChildrenSize();
        int childWidth = getChildren().stream().mapToInt(Node::getWidth).sum() + getSpacing() * (getChildren().size() - 1);
        int x = Align.getAlignedX(getAlignment().getHorizontalAlign(), this, childWidth);
        for (Node child : getChildren()) {
            int y = Align.getAlignedY(getAlignment().getVerticalAlign(), this, child.getHeight());
            child.setX(x);
            child.setY(y);
            x += child.getWidth() + getSpacing();
        }
    }

    protected void updateChildrenSize() {
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int childrenWidthWithoutWeight = getChildren().stream().filter(node -> getWeight(node) == 0).mapToInt(Node::getWidth).sum();
        int remainingWidth = getWidth() - getPadding().getHorizontal() - getSpacing() * (getChildren().size() - 1) - childrenWidthWithoutWeight;
        int weightedWidth = totalWeight > 0 ? remainingWidth / totalWeight : 0;
        getChildren().forEach(node -> {
            if (getWeight(node) > 0) {
                node.setParentPrefWidth(weightedWidth * getWeight(node));
            } else {
                node.setParentPrefHeight(NONE);
            }
            if (isFillHeight()) {
                node.setParentPrefHeight(getMaxChildrenHeight());
            } else {
                node.setParentPrefHeight(NONE);
            }
        });
    }
}
