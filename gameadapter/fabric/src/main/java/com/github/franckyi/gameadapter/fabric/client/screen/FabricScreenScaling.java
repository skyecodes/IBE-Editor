package com.github.franckyi.gameadapter.fabric.client.screen;

import com.github.franckyi.gameadapter.api.client.screen.ScreenScaling;
import com.github.franckyi.gameadapter.base.client.AbstractScreenScaling;
import net.minecraft.client.MinecraftClient;

public final class FabricScreenScaling extends AbstractScreenScaling {
    public static final ScreenScaling INSTANCE = new FabricScreenScaling();

    private FabricScreenScaling() {
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
