package com.github.franckyi.ibeeditor.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage {

    void write(PacketBuffer buffer);

    default void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> handle(contextSupplier.get()));
    }

    void handle(NetworkEvent.Context context);

}
