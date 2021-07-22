package com.github.franckyi.gameadapter.forge.client.screen;

import com.github.franckyi.gameadapter.api.client.screen.ScreenScaling;
import com.github.franckyi.gameadapter.base.client.AbstractScreenScaling;
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
        mc().resizeDisplay();
    }

    @Override
    protected void setScreenScale(int value) {
        mc().getWindow().setGuiScale(mc().getWindow().calculateScale(value, mc().isEnforceUnicode()));
        mc().screen.resize(mc(), mc().getWindow().getGuiScaledWidth(), mc().getWindow().getGuiScaledHeight());
    }

    @Override
    protected int getDefaultScale() {
        return mc().options.guiScale;
    }

    @Override
    public int getMaxScale() {
        return mc().getWindow().calculateScale(0, mc().isEnforceUnicode());
    }
}
