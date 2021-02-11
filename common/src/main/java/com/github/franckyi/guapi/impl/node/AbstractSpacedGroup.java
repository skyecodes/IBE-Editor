package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.SpacedGroup;
import com.github.franckyi.guapi.util.Align;

import java.util.Collection;

public abstract class AbstractSpacedGroup extends AbstractGroup implements SpacedGroup {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align> alignmentProperty = PropertyFactory.ofObject(Align.TOP_LEFT);

    protected AbstractSpacedGroup(int spacing, Collection<? extends Node> children) {
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
}
