package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.world.Item;

public class ItemListSelectionItemModel extends ListSelectionItemModel {
    private final Item item;

    public ItemListSelectionItemModel(String name, String id, Item item) {
        super(name, id);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
