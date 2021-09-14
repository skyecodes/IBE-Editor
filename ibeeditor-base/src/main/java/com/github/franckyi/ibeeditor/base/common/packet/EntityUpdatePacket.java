package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.ibeeditor.base.common.Packet;

public class EntityUpdatePacket implements Packet {
    private final int entityId;
    private final ICompoundTag entity;

    public EntityUpdatePacket(int entityId, ICompoundTag entity) {
        this.entityId = entityId;
        this.entity = entity;
    }

    public EntityUpdatePacket(IPacketBuffer buffer) {
        this(buffer.readInt(), buffer.readTag());
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeTag(entity);
    }

    public int getEntityId() {
        return entityId;
    }

    public ICompoundTag getEntity() {
        return entity;
    }
}
