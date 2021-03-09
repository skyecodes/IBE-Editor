package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.network.Packet;
import com.github.franckyi.minecraft.api.common.world.Item;

public class UpdatePlayerMainHandItemPacket implements Packet {
    private final Item item;

    public UpdatePlayerMainHandItemPacket(Item item) {
        this.item = item;
    }

    public UpdatePlayerMainHandItemPacket(Buffer buffer) {
        this(Minecraft.getCommon().createItem(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writeTag(item.getTag());
    }

    public Item getItem() {
        return item;
    }
}
