package com.github.franckyi.ibeeditor.common.network.editor.item;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerInventoryItemEditorMessage extends MainHandItemEditorMessage {

    protected final int slotIndex;

    public PlayerInventoryItemEditorMessage(ItemStack itemStack, int slotIndex) {
        super(itemStack);
        this.slotIndex = slotIndex;
    }

    public PlayerInventoryItemEditorMessage(PacketBuffer buffer) {
        super(buffer);
        this.slotIndex = buffer.readInt();
    }

    @Override
    public void write(PacketBuffer buffer) {
        super.write(buffer);
        buffer.writeInt(slotIndex);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ctx.getSender().inventory.setInventorySlotContents(slotIndex, itemStack);
    }

}
