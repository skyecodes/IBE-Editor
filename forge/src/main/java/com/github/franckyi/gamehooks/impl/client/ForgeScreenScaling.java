package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import net.minecraft.client.Minecraft;

public final class ForgeScreenScaling extends AbstractScreenScaling {
    public static final ScreenScaling INSTANCE = new ForgeScreenScaling();

    private ForgeScreenScaling() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    protected void resetScale() {
        mc().updateWindowSize();
    }

    @Override
    protected void setScreenScale(int value) {
        mc().getMainWindow().setGuiScale(mc().getMainWindow().calcGuiScale(value, mc().getForceUnicodeFont()));
        mc().currentScreen.resize(mc(), mc().getMainWindow().getScaledWidth(), mc().getMainWindow().getScaledHeight());
    }

    @Override
    protected int getDefaultScale() {
        return mc().gameSettings.guiScale;
    }

    @Override
    public int getMaxScale() {
        return mc().getMainWindow().calcGuiScale(0, mc().getForceUnicodeFont());
    }
}
