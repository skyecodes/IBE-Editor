package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.builder.ItemViewBuilder;
import net.minecraft.world.item.ItemStack;

public class ItemViewImpl extends AbstractItemView implements ItemViewBuilder {
    public ItemViewImpl() {
    }

    public ItemViewImpl(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return ItemView.class;
    }
}
