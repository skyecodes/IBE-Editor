package com.github.franckyi.ibeeditor.common.network.editor.entity;

import com.github.franckyi.ibeeditor.common.network.IMessage;
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
        this.nbt = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(id);
        buffer.writeCompoundTag(nbt);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        Entity entity = ctx.getSender().getServerWorld().getEntityByID(id);
        if (entity != null) {
            entity.read(nbt);
        }
    }
}
