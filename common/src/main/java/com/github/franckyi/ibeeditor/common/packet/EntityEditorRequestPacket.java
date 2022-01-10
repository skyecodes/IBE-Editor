package com.github.franckyi.ibeeditor.common.packet;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public class EntityEditorRequestPacket extends AbstractEditorRequestPacket {
    private final int entityId;

    public EntityEditorRequestPacket(EditorType editorType, int entityId) {
        super(editorType);
        this.entityId = entityId;
    }

    public EntityEditorRequestPacket(FriendlyByteBuf buffer) {
        super(buffer);
        entityId = buffer.readInt();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeInt(entityId);
    }

    public int getEntityId() {
        return entityId;
    }
}
