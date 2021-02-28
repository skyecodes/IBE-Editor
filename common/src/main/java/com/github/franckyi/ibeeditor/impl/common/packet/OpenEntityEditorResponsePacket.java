package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.network.Buffer;

public class OpenEntityEditorResponsePacket extends OpenEntityEditorRequestPacket {
    private final Entity entity;

    public OpenEntityEditorResponsePacket(OpenEntityEditorRequestPacket request, Entity entity) {
        super(request.getEntityId(), request.isNBT());
        this.entity = entity;
    }

    public OpenEntityEditorResponsePacket(Buffer buffer) {
        super(buffer);
        entity = GameHooks.common().createEntity(buffer.readTag());
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writeTag(entity.getTag());
    }

    public Entity getEntity() {
        return entity;
    }
}
