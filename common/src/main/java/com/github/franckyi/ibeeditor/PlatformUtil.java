package com.github.franckyi.ibeeditor;

import com.github.franckyi.ibeeditor.common.ClientPacketHandler;
import com.github.franckyi.ibeeditor.common.Packet;
import com.github.franckyi.ibeeditor.common.PacketReader;
import com.github.franckyi.ibeeditor.common.ServerPacketHandler;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;

import java.nio.file.Path;

public final class PlatformUtil {
    @ExpectPlatform
    public static Path getConfigDir() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToServer(String id, Packet packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void sendToClient(String id, ServerPlayer player, Packet packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ServerPacketHandler<P> handler) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, PacketReader<P> reader, ClientPacketHandler<P> handler) {
        throw new AssertionError();
    }
}
