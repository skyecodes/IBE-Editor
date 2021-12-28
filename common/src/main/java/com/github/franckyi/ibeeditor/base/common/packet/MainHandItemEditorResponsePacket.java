package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.io.IOException;

public class MainHandItemEditorResponsePacket extends MainHandItemEditorRequestPacket {
    private final ItemStack itemStack;

    public MainHandItemEditorResponsePacket(EditorType editorType, ItemStack itemStack) {
        super(editorType);
        this.itemStack = itemStack;
    }

    public MainHandItemEditorResponsePacket(FriendlyByteBuf buffer) {
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
