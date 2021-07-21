package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.world.Entity;

public class EntityEditorResponsePacket extends EntityEditorRequestPacket {
    private final Entity entity;

    public EntityEditorResponsePacket(EntityEditorRequestPacket request, Entity entity) {
        super(request.getEntityId(), request.getType());
        this.entity = entity;
    }

    public EntityEditorResponsePacket(Buffer buffer) {
        super(buffer);
        entity = Game.getCommon().createEntity(buffer.readTag());
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writeTag(entity.getData());
    }

    public Entity getEntity() {
        return entity;
    }
}
