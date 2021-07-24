package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.api.node.ItemView;

public interface GenericItemViewBuilder<N extends ItemView> extends ItemView, GenericControlBuilder<N> {
    default N item(Item value) {
        return with(n -> n.setItem(value));
    }
}
