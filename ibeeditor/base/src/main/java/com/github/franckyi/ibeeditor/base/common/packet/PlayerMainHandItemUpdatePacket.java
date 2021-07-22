package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.gameadapter.api.common.world.Item;

public class PlayerMainHandItemUpdatePacket implements Packet {
    private final Item item;

    public PlayerMainHandItemUpdatePacket(Item item) {
        this.item = item;
    }

    public PlayerMainHandItemUpdatePacket(Buffer buffer) {
        this(Game.getCommon().createItem(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeTag(item.getData());
    }

    public Item getItem() {
        return item;
    }
}
