package com.github.franckyi.ibeeditor.common.network;

import net.minecraft.resources.ResourceLocation;

public abstract class NetworkHandler<P> {
    private static int count;
    private final Class<P> type;
    private final ResourceLocation location;
    private final int id;
    private final PacketSerializer<P> serializer;

    protected NetworkHandler(Class<P> type, String location, PacketSerializer<P> serializer) {
        this.type = type;
        this.location = new ResourceLocation(location);
        this.id = count++;
        this.serializer = serializer;
    }

    public Class<P> getType() {
        return type;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public PacketSerializer<P> getSerializer() {
        return serializer;
    }
}
