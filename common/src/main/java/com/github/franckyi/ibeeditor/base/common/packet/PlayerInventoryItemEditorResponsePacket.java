package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.io.IOException;

public class PlayerInventoryItemEditorResponsePacket extends PlayerInventoryItemEditorRequestPacket {
    private final ItemStack itemStack;

    public PlayerInventoryItemEditorResponsePacket(EditorType editorType, int slotIndex, boolean isCreativeInventoryScreen, ItemStack itemStack) {
        super(editorType, slotIndex, isCreativeInventoryScreen);
        this.itemStack = itemStack;
    }

    public PlayerInventoryItemEditorResponsePacket(FriendlyByteBuf buffer) {
        super(buffer);
        itemStack = buffer.readItem();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeItem(itemStack);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
