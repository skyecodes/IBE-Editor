package com.github.franckyi.ibeeditor.client.clipboard.logic;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ItemClipboardEntry implements IClipboardEntry {

    private final ItemStack itemStack;

    public ItemClipboardEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemClipboardEntry(PacketBuffer buffer) {
        itemStack = buffer.readItemStack();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(itemStack);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemClipboardEntry that = (ItemClipboardEntry) o;

        return itemStack.equals(that.itemStack, false);

    }

    @Override
    public int hashCode() {
        return itemStack.hashCode();
    }
}
