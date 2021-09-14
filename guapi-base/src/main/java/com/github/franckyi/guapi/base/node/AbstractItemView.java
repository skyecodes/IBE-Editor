package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.guapi.api.node.ItemView;

public abstract class AbstractItemView extends AbstractControl implements ItemView {
    private final ObjectProperty<IItemStack> itemProperty = ObjectProperty.create();

    protected AbstractItemView() {
    }

    protected AbstractItemView(IItemStack itemStack) {
        setItem(itemStack);
    }

    @Override
    public ObjectProperty<IItemStack> itemProperty() {
        return itemProperty;
    }
}
