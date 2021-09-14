package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.Game;
import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.ibeeditor.base.common.Packet;

public class PlayerMainHandItemUpdatePacket implements Packet {
    private final IItemStack itemStack;

    public PlayerMainHandItemUpdatePacket(IItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public PlayerMainHandItemUpdatePacket(IPacketBuffer buffer) {
        this(Game.getCommon().createItemFromTag(buffer.readTag()));
    }

    @Override
    public void write(IPacketBuffer buffer) {
        buffer.writeTag(itemStack.getData());
    }

    public IItemStack getItem() {
        return itemStack;
    }
}
