package com.github.franckyi.ibeeditor.network.item;

import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.network.NetworkEvent;

public class EntityInventoryItemEditorMessage implements IMessage {

    private final ItemStack itemStack;
    private final int entityId;
    private final EntityEquipmentSlot slot;

    public EntityInventoryItemEditorMessage(PacketBuffer buffer) {
        itemStack = buffer.readItemStack();
        entityId = buffer.readInt();
        slot = buffer.readEnumValue(EntityEquipmentSlot.class);
    }

    public EntityInventoryItemEditorMessage(ItemStack is, EntityLiving entity, EntityEquipmentSlot slot) {
        this.itemStack = is;
        this.entityId = entity.getEntityId();
        this.slot = slot;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeItemStack(itemStack);
        buffer.writeInt(entityId);
        buffer.writeEnumValue(slot);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        WorldServer world = context.getSender().getServerWorld();
        Entity entity = world.getEntityByID(entityId);
        if (entity instanceof EntityLiving) {
            entity.setItemStackToSlot(slot, itemStack);
            world.tickEntity(entity, true);
        }
    }
}
