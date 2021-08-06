package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.builder.ItemViewBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class ItemViewImpl extends AbstractItemView implements ItemViewBuilder {
    public ItemViewImpl() {
    }

    public ItemViewImpl(IItemStack itemStack) {
        super(itemStack);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<ItemView> getType() {
        return NodeType.ITEM_VIEW;
    }
}
