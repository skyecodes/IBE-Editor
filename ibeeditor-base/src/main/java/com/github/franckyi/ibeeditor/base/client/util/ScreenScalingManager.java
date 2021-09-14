package com.github.franckyi.ibeeditor.base.client.util;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;

public abstract class ScreenScalingManager {
    private static ScreenScalingManager INSTANCE;

    public static void init(ScreenScalingManager instance) {
        INSTANCE = instance;
    }

    public static ScreenScalingManager get() {
        return INSTANCE;
    }

    protected final IntegerProperty scaleProperty = IntegerProperty.create();
    private final ObservableIntegerValue scalePropertyReadOnly = ObservableIntegerValue.readOnly(scaleProperty);
    protected final BooleanProperty canScaleBeResetProperty = BooleanProperty.create();
    private final ObservableBooleanValue canScaleBeResetPropertyReadOnly = ObservableBooleanValue.readOnly(canScaleBeResetProperty);

    protected ScreenScalingManager() {
        scaleProperty().addListener(this::setScreenScale);
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

    protected void setScale(int scale) {
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

    protected abstract void resetScale();

    protected abstract void setScreenScale(int value);

    protected abstract int getDefaultScale();

    protected abstract int getMaxScale();
}
