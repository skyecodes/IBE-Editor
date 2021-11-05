package com.github.franckyi.ibeeditor.common;

import net.minecraft.server.level.ServerPlayer;

@FunctionalInterface
public interface ServerPacketHandler<P> {
    void accept(P packet, ServerPlayer sender);
}
