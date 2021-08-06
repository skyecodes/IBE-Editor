package com.github.franckyi.ibeeditor.forge.client.util;

import com.github.franckyi.ibeeditor.base.client.util.ScreenScalingManager;
import net.minecraft.client.Minecraft;

public final class ForgeScreenScalingManager extends ScreenScalingManager {
    public static final ScreenScalingManager INSTANCE = new ForgeScreenScalingManager();

    private ForgeScreenScalingManager() {
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
