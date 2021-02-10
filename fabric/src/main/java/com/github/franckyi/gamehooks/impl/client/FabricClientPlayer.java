package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ClientPlayer;
import com.github.franckyi.gamehooks.impl.common.FabricPlayer;
import net.minecraft.client.MinecraftClient;

public class FabricClientPlayer extends FabricPlayer implements ClientPlayer {
    public static final ClientPlayer INSTANCE = new FabricClientPlayer();

    private FabricClientPlayer() {
        super(MinecraftClient.getInstance().player);
    }
}
