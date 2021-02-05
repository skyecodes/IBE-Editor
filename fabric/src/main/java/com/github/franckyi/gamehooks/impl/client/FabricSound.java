package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Sound;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class FabricSound implements Sound {
    public static final Sound INSTANCE = new FabricSound();

    private FabricSound() {
    }

    @Override
    public void playButtonSound() {
        MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
