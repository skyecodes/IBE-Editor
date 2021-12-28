package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

public class MainHandItemEditorRequestPacket extends AbstractEditorRequestPacket {
    public MainHandItemEditorRequestPacket(EditorType editorType) {
        super(editorType);
    }

    public MainHandItemEditorRequestPacket(FriendlyByteBuf buffer) {
        super(buffer);
    }
}
