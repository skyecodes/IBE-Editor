package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.ibeeditor.impl.common.EditorType;

public class EntityEditorRequestPacket implements Packet {
    private final int entityId;
    private final EditorType type;

    public EntityEditorRequestPacket(int entityId, EditorType type) {
        this.entityId = entityId;
        this.type = type;
    }

    public EntityEditorRequestPacket(Buffer buffer) {
        this(buffer.readInt(), EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(Buffer buffer) {
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
