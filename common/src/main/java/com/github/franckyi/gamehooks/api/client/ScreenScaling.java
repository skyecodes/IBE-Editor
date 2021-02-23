package com.github.franckyi.gamehooks.api.client;

public interface ScreenScaling {
    int getScaleOption();

    void setScaleOption(int scale);

    int getMaxScale();

    default int getScale() {
        return getScaleOption() == 0 ? getMaxScale() : getScaleOption();
    }

    default void scaleUp() {
        setScaleOption(getScale() + 1);
    }

    default void scaleDown() {
        setScaleOption(getScale() - 1);
    }

    default boolean canScaleUp() {
        return getScale() < getMaxScale();
    }

    default boolean canScaleDown() {
        return getScale() > 1;
    }
}
