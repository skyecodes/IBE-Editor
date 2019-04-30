package com.github.franckyi.ibeeditor.common.network;

import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage extends IPacket {

    @Override
    default void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
        ctx.get().enqueueWork(() -> handle(ctx.get()));
    }

    void handle(NetworkEvent.Context ctx);

}
