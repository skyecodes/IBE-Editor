package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ClientPlayer;
import com.github.franckyi.gamehooks.impl.common.ForgePlayer;
import net.minecraft.client.Minecraft;

public class ForgeClientPlayer extends ForgePlayer implements ClientPlayer {
    public static final ClientPlayer INSTANCE = new ForgeClientPlayer();

    private ForgeClientPlayer() {
        super(Minecraft.getInstance().player);
    }
}
