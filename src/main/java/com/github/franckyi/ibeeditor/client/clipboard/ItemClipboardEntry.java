package com.github.franckyi.ibeeditor.client.clipboard;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public class ItemClipboardEntry implements IClipboardEntry {

    private ItemStack itemStack;

    public ItemClipboardEntry(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemClipboardEntry(PacketBuffer buffer, boolean oldFormat) {
        if (oldFormat) {
            itemStack = buffer.readItemStack();
        } else {
            itemStack = ItemStack.read(buffer.readCompoundTag());
        }
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeCompoundTag(itemStack.write(new CompoundNBT()));
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
