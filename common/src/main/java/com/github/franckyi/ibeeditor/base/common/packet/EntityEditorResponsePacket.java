package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public class EntityEditorResponsePacket extends EntityEditorRequestPacket {
    private final CompoundTag tag;

    public EntityEditorResponsePacket(EditorType editorType, int entityId, CompoundTag tag) {
        super(editorType, entityId);
        this.tag = tag;
    }

    public EntityEditorResponsePacket(FriendlyByteBuf buffer) {
        super(buffer);
        tag = buffer.readNbt();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeNbt(tag);
    }

    public CompoundTag getTag() {
        return tag;
    }
}
