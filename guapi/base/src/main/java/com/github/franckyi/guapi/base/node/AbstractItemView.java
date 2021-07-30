package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.guapi.api.node.ItemView;

public abstract class AbstractItemView extends AbstractControl implements ItemView {
    private final ObjectProperty<Item> itemIdProperty = ObjectProperty.create();

    protected AbstractItemView() {
    }

    protected AbstractItemView(Item item) {
        setItem(item);
    }

    @Override
    public ObjectProperty<Item> itemProperty() {
        return itemIdProperty;
    }
}
