package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;

public class UpdatePlayerMainHandItemPacket implements Packet {
    private final Item item;

    public UpdatePlayerMainHandItemPacket(Item item) {
        this.item = item;
    }

    public UpdatePlayerMainHandItemPacket(Buffer buffer) {
        this(GameHooks.common().createItem(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeTag(item.getTag());
    }

    public Item getItem() {
        return item;
    }
}
