package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.guapi.api.node.ItemView;

public interface GenericItemViewBuilder<N extends ItemView> extends ItemView, GenericControlBuilder<N> {
    default N item(IItemStack value) {
        return with(n -> n.setItem(value));
    }
}
