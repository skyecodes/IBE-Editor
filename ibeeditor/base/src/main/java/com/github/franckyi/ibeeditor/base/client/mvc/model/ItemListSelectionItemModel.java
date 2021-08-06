package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;

public class ItemListSelectionItemModel extends ListSelectionItemModel {
    private final IItemStack itemStack;

    public ItemListSelectionItemModel(String name, IIdentifier id, IItemStack itemStack) {
        super(name, id);
        this.itemStack = itemStack;
    }

    public IItemStack getItem() {
        return itemStack;
    }

    @Override
    public Type getType() {
        return Type.ITEM;
    }
}
