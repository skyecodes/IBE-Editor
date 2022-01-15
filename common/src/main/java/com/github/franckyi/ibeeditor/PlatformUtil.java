package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.common.network.ClientNetworkHandler;
import com.github.franckyi.ibeeditor.common.network.ServerNetworkHandler;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;

import java.nio.file.Path;

public final class PlatformUtil {
    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void sendToServer(ServerNetworkHandler<P> handler, P packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void sendToClient(ClientNetworkHandler<P> handler, ServerPlayer player, P packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void registerServerHandler(ServerNetworkHandler<P> handler) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void registerClientHandler(ClientNetworkHandler<P> handler) {
        throw new AssertionError();
    }
}
