package com.github.franckyi.gamehooks.util.common.network;

@FunctionalInterface
public interface ClientPacketHandler<P> {
    void accept(P packet);
}
