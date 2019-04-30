package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IPacket {

    void write(PacketBuffer buffer);

    void handle(Supplier<NetworkEvent.Context> c);

}
