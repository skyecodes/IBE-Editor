package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.world.Item;

public interface ItemView extends Control {
    default Item getItem() {
        return itemProperty().getValue();
    }

    ObjectProperty<Item> itemProperty();

    default void setItem(Item value) {
        itemProperty().setValue(value);
    }
}
