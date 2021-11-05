package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class InventoryItemEntryModel extends EntryModel {
    private final ObjectProperty<ItemStack> itemProperty;
    private final IntegerProperty slotProperty;
    private final BiConsumer<ItemStack, Byte> action;

    public InventoryItemEntryModel(CategoryModel category, ItemStack item, byte slot, BiConsumer<ItemStack, Byte> action) {
        super(category);
        this.action = action;
        itemProperty = ObjectProperty.create(item);
        slotProperty = IntegerProperty.create(slot);
    }

    public byte getSlot() {
        return (byte) slotProperty().getValue();
    }

    public IntegerProperty slotProperty() {
        return slotProperty;
    }

    public void setSlot(byte value) {
        slotProperty().setValue(value);
    }

    public ItemStack getItem() {
        return itemProperty().getValue();
    }

    public ObjectProperty<ItemStack> itemProperty() {
        return itemProperty;
    }

    public void setItem(ItemStack value) {
        itemProperty().setValue(value);
    }

    @Override
    public void apply() {
        action.accept(getItem(), getSlot());
    }

    @Override
    public Type getType() {
        return Type.INVENTORY_ITEM;
    }
}
