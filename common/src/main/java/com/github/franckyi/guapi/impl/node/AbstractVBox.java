package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractVBox extends AbstractBox implements VBox {
    private final BooleanProperty fillWidthProperty = DataBindings.getPropertyFactory().createBooleanProperty();

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
    public BooleanProperty fillWidthProperty() {
        return fillWidthProperty;
    }

    @Override
    protected void updateChildren() {
        updateChildrenSize();
        int childHeight = getChildren().stream().mapToInt(Node::getHeight).sum() + getSpacing() * (getChildren().size() - 1);
        int y = Align.getAlignedY(getAlignment().getVerticalAlign(), this, childHeight);
        for (Node child : getChildren()) {
            int x = Align.getAlignedX(getAlignment().getHorizontalAlign(), this, child.getWidth());
            child.setX(x);
            child.setY(y);
            y += child.getHeight() + getSpacing();
        }
    }

    protected void updateChildrenSize() {
        int totalWeight = getChildren().stream().mapToInt(this::getWeight).sum();
        int childrenHeightWithoutWeight = getChildren().stream().filter(node -> getWeight(node) == 0).mapToInt(Node::getHeight).sum();
        int remainingHeight = getHeight() - getPadding().getVertical() - getSpacing() * (getChildren().size() - 1) - childrenHeightWithoutWeight;
        int weightedHeight = totalWeight > 0 ? remainingHeight / totalWeight : 0;
        getChildren().forEach(node -> {
            if (getWeight(node) > 0) {
                node.setParentPrefHeight(weightedHeight * getWeight(node));
            } else {
                node.setParentPrefHeight(NONE);
            }
            if (isFillWidth()) {
                node.setParentPrefWidth(getMaxChildrenWidth());
            } else {
                node.setParentPrefWidth(NONE);
            }
        });
    }
}
