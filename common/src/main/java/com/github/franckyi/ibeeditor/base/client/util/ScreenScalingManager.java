package com.github.franckyi.ibeeditor.base.client.util;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;
import net.minecraft.client.Minecraft;

public final class ScreenScalingManager {
    private static final ScreenScalingManager INSTANCE = new ScreenScalingManager();
    private final IntegerProperty scaleProperty = IntegerProperty.create();
    private final ObservableIntegerValue scalePropertyReadOnly = ObservableIntegerValue.readOnly(scaleProperty);
    private final BooleanProperty canScaleBeResetProperty = BooleanProperty.create();
    private final ObservableBooleanValue canScaleBeResetPropertyReadOnly = ObservableBooleanValue.readOnly(canScaleBeResetProperty);

    private ScreenScalingManager() {
        scaleProperty().addListener(this::setScreenScale);
    }

    public static ScreenScalingManager get() {
        return INSTANCE;
    }

    public void setBaseScale(int value) {
        if (value != -1) {
            setScale(value);
        } else {
            restoreScale();
        }
    }

    public int getScale() {
        return scaleProperty().getValue();
    }

    public ObservableIntegerValue scaleProperty() {
        return scalePropertyReadOnly;
    }

    private void setScale(int scale) {
        scaleProperty.setValue(scale);
        canScaleBeResetProperty.setValue(true);
    }

    public ObservableBooleanValue canScaleBeResetProperty() {
        return canScaleBeResetPropertyReadOnly;
    }

    public int getScaleAndReset() {
        int res = canScaleBeResetProperty.getValue() ? getScale() : -1;
        restoreScale();
        resetScale();
        return res;
    }

    public void restoreScale() {
        setScale(getDefaultScale());
        canScaleBeResetProperty.setValue(false);
    }

    public void scaleUp() {
        if (getScale() >= getMaxScale()) {
            setScale(0);
        } else {
            setScale(getScale() + 1);
        }
    }

    public void scaleDown() {
        if (getScale() == 0) {
            setScale(getMaxScale());
        } else {
            setScale(getScale() - 1);
        }
    }

    public boolean canScaleUp() {
        return getScale() != 0;
    }

    public boolean canScaleDown() {
        return getScale() != 1;
    }

    public void refresh() {
        setScreenScale(getScale());
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    private void resetScale() {
        mc().resizeDisplay();
    }

    private void setScreenScale(int value) {
        mc().getWindow().setGuiScale(mc().getWindow().calculateScale(value, mc().isEnforceUnicode()));
        mc().screen.resize(mc(), mc().getWindow().getGuiScaledWidth(), mc().getWindow().getGuiScaledHeight());
    }

    private int getDefaultScale() {
        return mc().options.guiScale;
    }

    public int getMaxScale() {
        return mc().getWindow().calculateScale(0, mc().isEnforceUnicode());
    }
}
