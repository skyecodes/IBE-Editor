package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.TreeView;

public interface GenericTreeViewBuilder<E extends TreeView.TreeItem<E>, N extends TreeView<E>> extends TreeView<E>, GenericListNodeBuilder<E, N> {
    default N root(E value) {
        return with(n -> n.setRoot(value));
    }

    default N showRoot(boolean value) {
        return with(n -> n.setShowRoot(value));
    }

    default N showRoot() {
        return showRoot(true);
    }

    default N childrenIncrement(int value) {
        return with(n -> n.setChildrenIncrement(value));
    }
}
