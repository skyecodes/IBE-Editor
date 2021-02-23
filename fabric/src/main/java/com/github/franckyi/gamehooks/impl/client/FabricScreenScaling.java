package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import net.minecraft.client.MinecraftClient;

public final class FabricScreenScaling implements ScreenScaling {
    public static final ScreenScaling INSTANCE = new FabricScreenScaling();
    private final IntegerProperty scaleProperty = PropertyFactory.ofInteger(mc().options.guiScale);

    private FabricScreenScaling() {
        scaleProperty().addListener(newVal -> mc().options.guiScale = newVal);
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    public void setScale(int scale) {
        ScreenScaling.super.setScale(scale);
        mc().onResolutionChanged();
    }

    @Override
    public IntegerProperty scaleProperty() {
        return scaleProperty;
    }

    @Override
    public int getMaxScale() {
        return mc().getWindow().calculateScaleFactor(0, mc().forcesUnicodeFont());
    }

}
