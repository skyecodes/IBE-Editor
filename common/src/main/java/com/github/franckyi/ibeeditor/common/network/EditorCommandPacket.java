package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

public record EditorCommandPacket(Target target, EditorType editorType) {
    public enum Target {
        WORLD, ITEM, BLOCK, ENTITY, SELF
    }

    public static final PacketSerializer<EditorCommandPacket> SERIALIZER = new PacketSerializer<>() {
        @Override
        public void write(EditorCommandPacket obj, FriendlyByteBuf buf) {
            buf.writeEnum(obj.target);
            buf.writeEnum(obj.editorType);
        }

        @Override
        public EditorCommandPacket read(FriendlyByteBuf buf) {
            return new EditorCommandPacket(
                    buf.readEnum(Target.class),
                    buf.readEnum(EditorType.class)
            );
        }
    };
}
