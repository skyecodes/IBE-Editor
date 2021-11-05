package com.github.franckyi.ibeeditor.common;

@FunctionalInterface
public interface ClientPacketHandler<P> {
    void accept(P packet);
}
