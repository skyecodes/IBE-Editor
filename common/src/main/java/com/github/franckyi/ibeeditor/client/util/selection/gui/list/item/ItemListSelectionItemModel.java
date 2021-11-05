package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ItemListSelectionItemModel extends ListSelectionItemModel {
    private final ItemStack itemStack;

    public ItemListSelectionItemModel(String name, ResourceLocation id, ItemStack itemStack) {
        super(name, id);
        this.itemStack = itemStack;
    }

    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public Type getType() {
        return Type.ITEM;
    }
}
