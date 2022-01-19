package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.common.network.NetworkHandler;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;

import java.nio.file.Path;

public final class PlatformUtil {
    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void registerServerHandler(NetworkHandler.Server<P> handler) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P> void registerClientHandler(NetworkHandler.Client<P> handler) {
        throw new AssertionError();
    }
}
