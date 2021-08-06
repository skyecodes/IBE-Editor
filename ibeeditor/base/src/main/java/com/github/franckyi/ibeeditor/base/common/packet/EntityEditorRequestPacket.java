package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Packet;

public class EntityEditorRequestPacket implements Packet {
    private final int entityId;
    private final EditorType type;

    public EntityEditorRequestPacket(int entityId, EditorType type) {
        this.entityId = entityId;
        this.type = type;
    }

    public EntityEditorRequestPacket(IPacketBuffer buffer) {
        this(buffer.readInt(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeByte(type.getId());
    }

    public int getEntityId() {
        return entityId;
    }

    public EditorType getType() {
        return type;
    }
}
