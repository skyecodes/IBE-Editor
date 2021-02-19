package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.ListNode;

public interface GenericListNodeBuilder<E, N extends ListNode<E>> extends ListNode<E>, GenericNodeBuilder<N> {
    default N itemHeight(int value) {
        return with(n -> n.setItemHeight(value));
    }

    default N fullWidth(int value) {
        return with(n -> {
            n.fullWidthProperty().unbind();
            n.setFullWidth(value);
        });
    }

    default N fullHeight(int value) {
        return with(n -> {
            n.fullHeightProperty().unbind();
            n.setFullHeight(value);
        });
    }

    default N baseX(int value) {
        return with(n -> {
            n.baseXProperty().unbind();
            n.setBaseX(value);
        });
    }

    default N baseY(int value) {
        return with(n -> {
            n.baseYProperty().unbind();
            n.setBaseY(value);
        });
    }

    default N renderer(Renderer<E> value) {
        return with(n -> n.setRenderer(value));
    }
}
