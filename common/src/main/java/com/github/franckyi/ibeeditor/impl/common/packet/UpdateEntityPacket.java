package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;

public class UpdateEntityPacket implements Packet {
    private final int entityId;
    private final Entity entity;

    public UpdateEntityPacket(int entityId, Entity entity) {
        this.entityId = entityId;
        this.entity = entity;
    }

    public UpdateEntityPacket(Buffer buffer) {
        this(buffer.readInt(), GameHooks.common().createEntity(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeInt(entityId);
        buffer.writeTag(entity.getTag());
    }

    public int getEntityId() {
        return entityId;
    }

    public Entity getEntity() {
        return entity;
    }
}
