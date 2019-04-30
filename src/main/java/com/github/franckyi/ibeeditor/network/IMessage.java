package com.github.franckyi.ibeeditor.network;

import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage extends IPacket {

    @Override
    default void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> handle(contextSupplier.get()));
    }

    void handle(NetworkEvent.Context context);

}
