package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractVBox extends AbstractGroup implements VBox {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align.Horizontal> alignmentProperty = PropertyFactory.ofObject(Align.Horizontal.LEFT);

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
        super(children);
        setSpacing(spacing);
        spacingProperty().addListener(this::shouldComputeSize);
        alignmentProperty().addListener(this::shouldUpdateChildrenPos);
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
    public Align.Horizontal getAlignment() {
        return alignmentProperty().getValue();
    }

    @Override
    public ObjectProperty<Align.Horizontal> alignmentProperty() {
        return alignmentProperty;
    }

    @Override
    public void setAlignment(Align.Horizontal value) {
        alignmentProperty().setValue(value);
    }

    @Override
    protected void updateChildrenPos() {
        int y = getY() + getPadding().getTop();
        for (Node child : getChildren()) {
            int x = Align.getAlignedX(getAlignment(), this, child.getWidth());
            child.setX(x);
            child.setY(y);
            y += child.getHeight() + getSpacing();
        }
    }
}
