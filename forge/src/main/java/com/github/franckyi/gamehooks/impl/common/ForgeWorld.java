package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.World;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;

public class ForgeWorld implements World {
    public static final World INSTANCE = new ForgeWorld();

    private ForgeWorld() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    private ClientWorld world() {
        return mc().world;
    }
}
