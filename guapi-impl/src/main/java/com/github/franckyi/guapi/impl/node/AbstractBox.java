package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Box;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.Align;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBox extends AbstractGroup implements Box {
    private final IntegerProperty spacingProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final ObjectProperty<Align> alignmentProperty = DataBindings.getPropertyFactory().createObjectProperty(Align.TOP_LEFT);
    protected final Map<Node, Integer> weightMap = new HashMap<>();

    protected AbstractBox(int spacing, Collection<? extends Node> children) {
        super(children);
        setSpacing(spacing);
        spacingProperty().addListener(this::shouldComputeSize);
        alignmentProperty().addListener(this::shouldUpdateChildren);
    }

    @Override
    public int getSpacing() {
        return spacingProperty().getValue();
    }

    @Override
    public IntegerProperty spacingProperty() {
        return spacingProperty;
    }

    @Override
    public void setSpacing(int value) {
        spacingProperty().setValue(value);
    }

    @Override
    public Align getAlignment() {
        return alignmentProperty().getValue();
    }

    @Override
    public ObjectProperty<Align> alignmentProperty() {
        return alignmentProperty;
    }

    @Override
    public void setAlignment(Align value) {
        alignmentProperty().setValue(value);
    }

    @Override
    public void setWeight(Node node, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (!getChildren().contains(node)) {
            throw new IllegalArgumentException("This WeightedVBox doesn't contain the Node " + node);
        }
        Integer old = weightMap.put(node, value);
        if (old != null && old == value) {
            shouldUpdateChildren();
        }
    }

    @Override
    public void resetWeight(Node node) {
        if (weightMap.remove(node) != 0) {
            shouldUpdateChildren();
        }
    }

    @Override
    public int getWeight(Node node) {
        Integer value = weightMap.get(node);
        if (value == null) {
            return 0;
        }
        return value;
    }
}
