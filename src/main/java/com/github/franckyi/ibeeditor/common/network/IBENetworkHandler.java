package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.common.network.editor.OpenEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.block.BlockEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.common.network.editor.block.InitBlockEditorResponse;
import com.github.franckyi.ibeeditor.common.network.editor.entity.EntityEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.EntityInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.PlayerInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.handshake.C2SHandshake;
import com.github.franckyi.ibeeditor.common.network.handshake.S2CHandshake;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Function;

public class IBENetworkHandler {

    private static final String CHANNEL_VERSION = "1.0.0";
    private static final SimpleChannel MOD_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(IBEEditorMod.MODID, "network"))
            .clientAcceptedVersions(version -> true)
            .serverAcceptedVersions(version -> true)
            .networkProtocolVersion(() -> CHANNEL_VERSION)
            .simpleChannel();

    private static int i;

    public static SimpleChannel getModChannel() {
        return MOD_CHANNEL;
    }

    public static void init() {
        // Handshake
        registerPacket(S2CHandshake.class, S2CHandshake::new);
        registerPacket(C2SHandshake.class, C2SHandshake::new);
        // Command
        registerPacket(OpenEditorMessage.class, OpenEditorMessage::new);
        // Item Editor
        registerPacket(MainHandItemEditorMessage.class, MainHandItemEditorMessage::new);
        registerPacket(PlayerInventoryItemEditorMessage.class, PlayerInventoryItemEditorMessage::new);
        registerPacket(BlockInventoryItemEditorMessage.class, BlockInventoryItemEditorMessage::new);
        registerPacket(EntityInventoryItemEditorMessage.class, EntityInventoryItemEditorMessage::new);
        // Block Editor
        registerPacket(InitBlockEditorRequest.class, InitBlockEditorRequest::new);
        registerPacket(InitBlockEditorResponse.class, InitBlockEditorResponse::new);
        registerPacket(BlockEditorMessage.class, BlockEditorMessage::new);
        // Entity editor
        registerPacket(EntityEditorMessage.class, EntityEditorMessage::new);
    }

    private static <P extends IPacket> void registerPacket(Class<P> c, Function<PacketBuffer, P> read) {
        MOD_CHANNEL.messageBuilder(c, i++)
                .decoder(read)
                .encoder(IPacket::write)
                .consumer(IPacket::handle)
                .add();
    }

}
