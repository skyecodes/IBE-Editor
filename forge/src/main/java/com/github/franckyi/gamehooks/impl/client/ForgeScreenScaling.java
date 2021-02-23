package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import net.minecraft.client.Minecraft;

public final class ForgeScreenScaling implements ScreenScaling {
    public static final ScreenScaling INSTANCE = new ForgeScreenScaling();
    private final IntegerProperty scaleProperty = PropertyFactory.ofInteger(mc().gameSettings.guiScale);

    private ForgeScreenScaling() {
        scaleProperty().addListener(newVal -> mc().gameSettings.guiScale = newVal);
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public void setScale(int scale) {
        ScreenScaling.super.setScale(scale);
        mc().updateWindowSize();
    }

    @Override
    public IntegerProperty scaleProperty() {
        return scaleProperty;
    }

    @Override
    public int getMaxScale() {
        return mc().getMainWindow().calcGuiScale(0, mc().getForceUnicodeFont());
    }
}
