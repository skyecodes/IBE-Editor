package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.TreeViewBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class TreeViewImpl<E extends TreeView.TreeItem<E>> extends AbstractTreeView<E> implements TreeViewBuilder<E> {
    public TreeViewImpl() {
    }

    public TreeViewImpl(int itemHeight) {
        super(itemHeight);
    }

    public TreeViewImpl(int itemHeight, E root) {
        super(itemHeight, root);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.TREE_VIEW;
    }
}
