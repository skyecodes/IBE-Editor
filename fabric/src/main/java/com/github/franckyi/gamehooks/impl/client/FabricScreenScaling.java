package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import net.minecraft.client.MinecraftClient;

public final class FabricScreenScaling implements ScreenScaling {
    public static final ScreenScaling INSTANCE = new FabricScreenScaling();

    private FabricScreenScaling() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    public int getScaleOption() {
        return mc().options.guiScale;
    }

    @Override
    public void setScaleOption(int scale) {
        mc().options.guiScale = scale;
        mc().onResolutionChanged();
    }

    @Override
    public int getMaxScale() {
        return mc().getWindow().calculateScaleFactor(0, mc().forcesUnicodeFont());
    }

}
