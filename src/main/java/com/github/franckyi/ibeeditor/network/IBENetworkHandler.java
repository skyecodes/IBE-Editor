package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.network.IBEHandshakeMessages.IBEHandshake;
import com.github.franckyi.ibeeditor.network.block.BlockEditorMessage;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorResponse;
import com.github.franckyi.ibeeditor.network.entity.EntityEditorMessage;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.EntityInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collections;
import java.util.function.Function;

public class IBENetworkHandler {

    private static final String CHANNEL_VERSION = "1.0.0";
    private static final SimpleChannel MOD_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(IBEEditorMod.MODID, "network"))
            .clientAcceptedVersions(version -> true)
            .serverAcceptedVersions(version -> true)
            .networkProtocolVersion(() -> CHANNEL_VERSION)
            .simpleChannel();
    private static final String HANDSHAKE_VERSION = "1.0.0";
    private static final SimpleChannel HANDSHAKE_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(IBEEditorMod.MODID, "handshake"))
            .clientAcceptedVersions(version -> true)
            .serverAcceptedVersions(version -> true)
            .networkProtocolVersion(() -> HANDSHAKE_VERSION)
            .simpleChannel();
    private static int i, j;

    public static SimpleChannel getModChannel() {
        return MOD_CHANNEL;
    }

    static SimpleChannel getHandshakeChannel() {
        return HANDSHAKE_CHANNEL;
    }

    public static void init() {
        // Command
        registerMessage(OpenEditorMessage.class, OpenEditorMessage::new);
        // Item Editor
        registerMessage(MainHandItemEditorMessage.class, MainHandItemEditorMessage::new);
        registerMessage(PlayerInventoryItemEditorMessage.class, PlayerInventoryItemEditorMessage::new);
        registerMessage(BlockInventoryItemEditorMessage.class, BlockInventoryItemEditorMessage::new);
        registerMessage(EntityInventoryItemEditorMessage.class, EntityInventoryItemEditorMessage::new);
        // Block Editor
        registerMessage(InitBlockEditorRequest.class, InitBlockEditorRequest::new);
        registerMessage(InitBlockEditorResponse.class, InitBlockEditorResponse::new);
        registerMessage(BlockEditorMessage.class, BlockEditorMessage::new);
        // Entity editor
        registerMessage(EntityEditorMessage.class, EntityEditorMessage::new);
        // Handshake
        registerHandshake(IBEHandshakeMessages.S2CHandshake.class, IBEHandshakeMessages.S2CHandshake::new, new IBEHandshakeMessages.S2CHandshake());
        registerHandshake(IBEHandshakeMessages.C2SHandshake.class, IBEHandshakeMessages.C2SHandshake::new);
    }

    private static <MSG extends IMessage> void registerMessage(Class<MSG> c, Function<PacketBuffer, MSG> read) {
        MOD_CHANNEL.messageBuilder(c, i++)
                .decoder(read)
                .encoder(IMessage::write)
                .consumer(IMessage::handle)
                .add();
    }

    private static <MSG extends IBEHandshake> void registerHandshake(Class<MSG> c, Function<PacketBuffer, MSG> read) {
        handshakeBuilder(c, read).add();
    }

    private static <MSG extends IBEHandshake> void registerHandshake(Class<MSG> c, Function<PacketBuffer, MSG> read, MSG loginPacket) {
        handshakeBuilder(c, read).buildLoginPacketList(
                isLocal -> Collections.singletonList(new ImmutablePair<>("IBE handshake", loginPacket)))
                .add();
    }

    private static <MSG extends IBEHandshake> SimpleChannel.MessageBuilder<MSG> handshakeBuilder(Class<MSG> c, Function<PacketBuffer, MSG> read) {
        return HANDSHAKE_CHANNEL.messageBuilder(c, j++)
                .loginIndex(IBEHandshake::getLoginIndex, IBEHandshake::setLoginIndex)
                .decoder(read)
                .encoder(IBEHandshake::write)
                .consumer(IBEHandshake::handle);
    }

}
