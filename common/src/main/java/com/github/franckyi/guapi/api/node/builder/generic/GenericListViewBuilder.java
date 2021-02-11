package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.ListView;

public interface GenericListViewBuilder<E, N extends ListView<E>> extends ListView<E>, GenericNodeBuilder<N> {
    default N items(E... items) {
        return with(n -> n.getItems().addAll(items));
    }

    default N itemHeight(int value) {
        return with(n -> n.setItemHeight(value));
    }

    default N fullWidth(int value) {
        return with(n -> n.setFullWidth(value));
    }

    default N fullHeight(int value) {
        return with(n -> n.setFullHeight(value));
    }

    default N baseX(int value) {
        return with(n -> n.setBaseX(value));
    }

    default N baseY(int value) {
        return with(n -> n.setBaseY(value));
    }

    default N renderer(Renderer<E> value) {
        return with(n -> n.setRenderer(value));
    }
}
