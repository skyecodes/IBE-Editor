package com.github.franckyi.ibeeditor.fabric.client.util;

import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import net.minecraft.client.MinecraftClient;

public final class FabricScreenScalingManager extends ScreenScalingManager {
    public static final ScreenScalingManager INSTANCE = new FabricScreenScalingManager();

    private FabricScreenScalingManager() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
    }

    @Override
    protected void resetScale() {
        mc().onResolutionChanged();
    }

    @Override
    protected void setScreenScale(int value) {
        mc().getWindow().setScaleFactor(mc().getWindow().calculateScaleFactor(value, mc().forcesUnicodeFont()));
        mc().currentScreen.resize(mc(), mc().getWindow().getScaledWidth(), mc().getWindow().getScaledHeight());
    }

    @Override
    protected int getDefaultScale() {
        return mc().options.guiScale;
    }

    @Override
    public int getMaxScale() {
        return mc().getWindow().calculateScaleFactor(0, mc().forcesUnicodeFont());
    }
}
