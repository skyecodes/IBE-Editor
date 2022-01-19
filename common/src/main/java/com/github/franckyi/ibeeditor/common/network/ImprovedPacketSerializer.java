package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;

public interface ImprovedPacketSerializer<T> extends PacketSerializer<T> {
    void read(T obj, FriendlyByteBuf buf);

    T createInstance();

    @Override
    default T read(FriendlyByteBuf buf) {
        T obj = createInstance();
        read(obj, buf);
        return obj;
    }
}
