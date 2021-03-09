package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.TreeView;

public abstract class AbstractTreeView<E extends TreeView.TreeItem<E>> extends AbstractListNode<E> implements TreeView<E> {
    private final ObjectProperty<E> rootItemProperty = Bindings.getPropertyFactory().ofObject();
    private final BooleanProperty showRootProperty = Bindings.getPropertyFactory().ofBoolean();
    private final IntegerProperty childrenIncrementProperty = Bindings.getPropertyFactory().ofInteger(20);

    protected AbstractTreeView() {
        this(0);
    }

    protected AbstractTreeView(int itemHeight) {
        this(itemHeight, null);
    }

    protected AbstractTreeView(int itemHeight, E root) {
        super(itemHeight);
        setRoot(root);
    }

    @Override
    public ObjectProperty<E> rootItemProperty() {
        return rootItemProperty;
    }

    @Override
    public BooleanProperty showRootProperty() {
        return showRootProperty;
    }

    @Override
    public IntegerProperty childrenIncrementProperty() {
        return childrenIncrementProperty;
    }
}
