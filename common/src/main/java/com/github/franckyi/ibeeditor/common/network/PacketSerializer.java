package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.FriendlyByteBuf;

public interface PacketSerializer<T> {
    void write(T obj, FriendlyByteBuf buf);

    T read(FriendlyByteBuf buf);

    static <T> PacketSerializer<T> empty(T instance) {
        return new PacketSerializer<>() {
            @Override
            public void write(T obj, FriendlyByteBuf buf) {
            }

            @Override
            public T read(FriendlyByteBuf buf) {
                return instance;
            }
        };
    }
}
