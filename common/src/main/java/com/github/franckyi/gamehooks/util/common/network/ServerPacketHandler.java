package com.github.franckyi.gamehooks.util.common.network;

import com.github.franckyi.gamehooks.api.common.Player;

@FunctionalInterface
public interface ServerPacketHandler<P> {
    void accept(P packet, Player sender);
}
