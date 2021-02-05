package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Sound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvents;

public class ForgeSound implements Sound {
    public static final Sound INSTANCE = new ForgeSound();

    private ForgeSound() {
    }

    @Override
    public void playButtonSound() {
        Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
