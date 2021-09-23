package com.github.franckyi.ibeeditor.base.common;

import net.minecraft.network.FriendlyByteBuf;

public interface Packet {
    void write(FriendlyByteBuf buffer) throws Exception;
}
