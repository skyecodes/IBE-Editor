package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.util.Align;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractHBox extends AbstractGroup implements HBox {
    private final IntegerProperty spacingProperty = PropertyFactory.ofInteger();
    private final ObjectProperty<Align.Vertical> alignmentProperty = PropertyFactory.ofObject(Align.Vertical.TOP);

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
    public Align.Vertical getAlignment() {
        return alignmentProperty().getValue();
    }

    @Override
    public ObjectProperty<Align.Vertical> alignmentProperty() {
        return alignmentProperty;
    }

    @Override
    public void setAlignment(Align.Vertical value) {
        alignmentProperty().setValue(value);
    }

    @Override
    protected void updateChildrenPos() {
        int x = getX() + getPadding().getLeft();
        for (Node child : getChildren()) {
            int y = Align.getAlignedY(getAlignment(), this, child.getHeight());
            child.setX(x);
            child.setY(y);
            x += child.getWidth() + getSpacing();
        }
    }
}
