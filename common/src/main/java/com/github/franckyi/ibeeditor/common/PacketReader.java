package com.github.franckyi.ibeeditor.common;

import net.minecraft.network.FriendlyByteBuf;

@FunctionalInterface
public interface PacketReader<P> {
    P read(FriendlyByteBuf buffer) throws Exception;
}
