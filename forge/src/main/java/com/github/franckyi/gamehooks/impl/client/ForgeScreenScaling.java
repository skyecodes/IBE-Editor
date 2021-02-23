package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import net.minecraft.client.Minecraft;

public final class ForgeScreenScaling implements ScreenScaling {
    public static final ScreenScaling INSTANCE = new ForgeScreenScaling();

    private ForgeScreenScaling() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public int getScaleOption() {
        return mc().gameSettings.guiScale;
    }

    @Override
    public void setScaleOption(int scale) {
        mc().gameSettings.guiScale = scale;
        mc().updateWindowSize();
    }

    @Override
    public int getMaxScale() {
        return mc().getMainWindow().calcGuiScale(0, mc().getForceUnicodeFont());
    }
}
