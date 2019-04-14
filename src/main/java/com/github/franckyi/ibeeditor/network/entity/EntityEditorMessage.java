package com.github.franckyi.ibeeditor.network.entity;

import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class EntityEditorMessage implements IMessage {

    private int id;
    private NBTTagCompound nbt;

    public EntityEditorMessage(Entity entity) {
        this.id = entity.getEntityId();
        this.nbt = entity.writeWithoutTypeId(new NBTTagCompound());
    }

    public EntityEditorMessage(PacketBuffer buffer) {
        this.id = buffer.readInt();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(id);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        Entity entity = context.getSender().getServerWorld().getEntityByID(id);
        if (entity != null) {
            entity.read(nbt);
        }
    }
}
