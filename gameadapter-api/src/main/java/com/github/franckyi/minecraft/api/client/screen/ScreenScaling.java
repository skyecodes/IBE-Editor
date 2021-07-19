package com.github.franckyi.minecraft.api.client.screen;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableIntegerValue;

public interface ScreenScaling {
    int getScale();

    ObservableIntegerValue scaleProperty();

    void setBaseScale(int value);

    ObservableBooleanValue canScaleBeResetProperty();

    int getScaleAndReset();

    void restoreScale();

    void scaleUp();

    void scaleDown();

    boolean canScaleUp();

    boolean canScaleDown();

    void refresh();
}
