package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public class PlayerInventoryItemEditorRequestPacket extends AbstractEditorRequestPacket {
    private final int slotIndex;
    private final boolean isCreativeInventoryScreen;

    public PlayerInventoryItemEditorRequestPacket(EditorType editorType, int slotIndex, boolean isCreativeInventoryScreen) {
        super(editorType);
        this.slotIndex = slotIndex;
        this.isCreativeInventoryScreen = isCreativeInventoryScreen;
    }

    public PlayerInventoryItemEditorRequestPacket(FriendlyByteBuf buffer) {
        super(buffer);
        slotIndex = buffer.readInt();
        isCreativeInventoryScreen = buffer.readBoolean();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeInt(slotIndex);
        buffer.writeBoolean(isCreativeInventoryScreen);
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public boolean isCreativeInventoryScreen() {
        return isCreativeInventoryScreen;
    }
}
