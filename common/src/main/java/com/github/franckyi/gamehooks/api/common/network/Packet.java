package com.github.franckyi.gamehooks.api.common.network;

public interface Packet {
    void write(Buffer<?> buffer);
}
