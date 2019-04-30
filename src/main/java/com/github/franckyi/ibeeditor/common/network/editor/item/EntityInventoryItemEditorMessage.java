package com.github.franckyi.ibeeditor.common.network.editor.item;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.CapabilityItemHandler;

public class EntityInventoryItemEditorMessage extends PlayerInventoryItemEditorMessage {

    protected final int entityId;

    public EntityInventoryItemEditorMessage(ItemStack itemStack, int slotIndex, int entityId) {
        super(itemStack, slotIndex);
        this.entityId = entityId;
    }

    public EntityInventoryItemEditorMessage(PacketBuffer buffer) {
        super(buffer);
        entityId = buffer.readInt();
    }

    @Override
    public void write(PacketBuffer buffer) {
        super.write(buffer);
        buffer.writeInt(entityId);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        ctx.getSender().getServerWorld().getEntityByID(entityId).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
            itemHandler.extractItem(slotIndex, itemHandler.getStackInSlot(slotIndex).getCount(), false);
            itemHandler.insertItem(slotIndex, itemStack, false);
        });
    }
}
