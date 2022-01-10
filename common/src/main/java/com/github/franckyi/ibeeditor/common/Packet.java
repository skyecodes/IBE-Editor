package com.github.franckyi.ibeeditor.common;

import net.minecraft.network.FriendlyByteBuf;

public interface Packet {
    void write(FriendlyByteBuf buffer) throws Exception;
}
