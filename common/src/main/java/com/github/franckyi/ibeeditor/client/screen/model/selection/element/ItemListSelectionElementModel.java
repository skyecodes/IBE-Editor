package com.github.franckyi.ibeeditor.client.screen.model.selection.element;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ItemListSelectionElementModel extends ListSelectionElementModel {
    private final ItemStack itemStack;

    public ItemListSelectionElementModel(String name, ResourceLocation id, ItemStack itemStack) {
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
