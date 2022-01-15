package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;

public interface PacketSerializer<T> {
    void write(T obj, FriendlyByteBuf buf);

    T read(FriendlyByteBuf buf);
}
