package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.world.Entity;

public class OpenEntityEditorResponsePacket extends OpenEntityEditorRequestPacket {
    private final Entity entity;

    public OpenEntityEditorResponsePacket(OpenEntityEditorRequestPacket request, Entity entity) {
        super(request.getEntityId(), request.isNBT());
        this.entity = entity;
    }

    public OpenEntityEditorResponsePacket(Buffer buffer) {
        super(buffer);
        entity = Minecraft.getCommon().createEntity(buffer.readTag());
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
