package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.io.IOException;

public class BlockInventoryItemEditorResponsePacket extends BlockInventoryItemEditorRequestPacket {
    private final ItemStack itemStack;

    public BlockInventoryItemEditorResponsePacket(EditorType type, int slotIndex, BlockPos pos, ItemStack itemStack) {
        super(type, slotIndex, pos);
        this.itemStack = itemStack;
    }

    public BlockInventoryItemEditorResponsePacket(FriendlyByteBuf buffer) {
        super(buffer);
        itemStack = buffer.readItem();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
