package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;

public interface ItemView extends Control {
    default IItemStack getItem() {
        return itemProperty().getValue();
    }

    ObjectProperty<IItemStack> itemProperty();

    default void setItem(IItemStack value) {
        itemProperty().setValue(value);
    }
}
