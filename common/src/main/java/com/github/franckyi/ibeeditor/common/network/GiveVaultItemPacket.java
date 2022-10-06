package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public record GiveVaultItemPacket(int slot, ItemStack itemStack) {
    public static final PacketSerializer<GiveVaultItemPacket> SERIALIZER = new PacketSerializer<>() {
        @Override
        public void write(GiveVaultItemPacket obj, FriendlyByteBuf buf) {
            buf.writeInt(obj.slot);
            buf.writeItem(obj.itemStack);
        }

        @Override
        public GiveVaultItemPacket read(FriendlyByteBuf buf) {
            return new GiveVaultItemPacket(buf.readInt(), buf.readItem());
        }
    };
}
