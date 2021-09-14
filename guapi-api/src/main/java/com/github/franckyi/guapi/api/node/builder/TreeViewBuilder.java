package com.github.franckyi.guapi.api.node.builder;

import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.guapi.api.node.builder.generic.GenericTreeViewBuilder;

public interface TreeViewBuilder<E extends TreeView.TreeItem<E>> extends GenericTreeViewBuilder<E, TreeViewBuilder<E>> {
}
