package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;

public class UpdateEntityPacket implements Packet {
    private final int entityId;
    private final ObjectTag tag;

    public UpdateEntityPacket(int entityId, ObjectTag tag) {
        this.entityId = entityId;
        this.tag = tag;
    }

    public UpdateEntityPacket(Buffer buffer) {
        this(buffer.readInt(), buffer.readTag());
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeTag(tag);
    }

    public int getEntityId() {
        return entityId;
    }

    public ObjectTag getTag() {
        return tag;
    }
}
