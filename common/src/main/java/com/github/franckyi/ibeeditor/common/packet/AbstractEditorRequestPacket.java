package com.github.franckyi.ibeeditor.common.packet;

import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.Packet;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public abstract class AbstractEditorRequestPacket implements Packet {
    private final EditorType editorType;

    public AbstractEditorRequestPacket(EditorType editorType) {
        this.editorType = editorType;
    }

    public AbstractEditorRequestPacket(FriendlyByteBuf buffer) {
        this(EditorType.from(buffer.readByte()));
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        buffer.writeByte(editorType.getId());
    }

    public EditorType getEditorType() {
        return editorType;
    }
}
