package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.ListView;

public interface GenericListViewBuilder<E, N extends ListView<E>> extends ListView<E>, GenericListNodeBuilder<E, N> {
    default N items(E... items) {
        return with(n -> n.getItems().addAll(items));
    }
}
