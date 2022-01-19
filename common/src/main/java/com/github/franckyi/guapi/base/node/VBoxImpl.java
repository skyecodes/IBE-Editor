package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.VBox;
import com.github.franckyi.guapi.api.node.builder.VBoxBuilder;

import java.util.Collection;

public class VBoxImpl extends AbstractVBox implements VBoxBuilder {
    public VBoxImpl() {
    }

    public VBoxImpl(int spacing) {
        super(spacing);
    }

    public VBoxImpl(Node... children) {
        super(children);
    }

    public VBoxImpl(Collection<? extends Node> children) {
        super(children);
    }

    public VBoxImpl(int spacing, Node... children) {
        super(spacing, children);
    }

    public VBoxImpl(int spacing, Collection<? extends Node> children) {
        super(spacing, children);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return VBox.class;
    }

    @Override
    public String toString() {
        return "VBox" + getChildren();
    }
}
