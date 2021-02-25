package com.github.franckyi.gamehooks.util.common;

@FunctionalInterface
public interface ClientPacketHandler<P> {
    void accept(P packet);
}
