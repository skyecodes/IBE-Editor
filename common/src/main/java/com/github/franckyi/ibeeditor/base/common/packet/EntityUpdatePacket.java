package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class EntityUpdatePacket implements Packet {
    private final int entityId;
    private final CompoundTag entity;

    public EntityUpdatePacket(int entityId, CompoundTag entity) {
        this.entityId = entityId;
        this.entity = entity;
    }

    public EntityUpdatePacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readNbt());
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeNbt(entity);
    }

    public int getEntityId() {
        return entityId;
    }

    public CompoundTag getEntity() {
        return entity;
    }
}
