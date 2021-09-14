package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;

public class EntityEditorResponsePacket extends EntityEditorRequestPacket {
    private final ICompoundTag entity;

    public EntityEditorResponsePacket(EntityEditorRequestPacket request, ICompoundTag entity) {
        super(request.getEntityId(), request.getType());
        this.entity = entity;
    }

    public EntityEditorResponsePacket(IPacketBuffer buffer) {
        super(buffer);
        entity = buffer.readTag();
    }

    @Override
    public void write(IPacketBuffer buffer) {
        super.write(buffer);
        buffer.writeTag(entity);
    }

    public ICompoundTag getEntity() {
        return entity;
    }
}
