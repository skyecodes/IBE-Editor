package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.World;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class FabricWorld implements World {
    public static final World INSTANCE = new FabricWorld();

    private FabricWorld() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    private ClientWorld world() {
        return mc().world;
    }
}
