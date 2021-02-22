package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.gamehooks.api.common.network.Packet;
import com.github.franckyi.gamehooks.util.common.network.ClientPacketHandler;
import com.github.franckyi.gamehooks.util.common.network.ServerPacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ForgeNetwork implements Network<ServerPlayerEntity> {
    public static final Network<ServerPlayerEntity> INSTANCE = new ForgeNetwork();

    private final String VERSION = "1";
    private final SimpleChannel channel = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("ibeeditor:channel"),
            () -> VERSION,
            VERSION::equals,
            VERSION::equals
    );

    private ForgeNetwork() {
    }

    @Override
    public void sendToServer(String id, Packet packet) {
        channel.sendToServer(packet);
    }

    @Override
    public void sendToClient(String id, ServerPlayerEntity entity, Packet packet) {
        channel.sendTo(packet, entity.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public <P extends Packet> void registerServerHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ServerPacketHandler<P> handler) {
        channel.messageBuilder(msgClass, id1)
                .decoder(buffer -> reader.apply(new ForgeBuffer(buffer)))
                .encoder((p, buffer) -> p.write(new ForgeBuffer(buffer)))
                .consumer((BiConsumer<P, Supplier<NetworkEvent.Context>>) (msg, ctx) -> ctx.get().enqueueWork(() -> handler.accept(msg, new ForgeServerPlayer(ctx.get().getSender()))))
                .add();
    }

    @Override
    public <P extends Packet> void registerClientHandler(String id, int id1, Class<P> msgClass, Function<Buffer, P> reader, ClientPacketHandler<P> handler) {
        channel.messageBuilder(msgClass, id1)
                .decoder(buffer -> reader.apply(new ForgeBuffer(buffer)))
                .encoder((p, buffer) -> p.write(new ForgeBuffer(buffer)))
                .consumer((BiConsumer<P, Supplier<NetworkEvent.Context>>) (msg, ctx) -> ctx.get().enqueueWork(() -> handler.accept(msg)))
                .add();
    }
}
