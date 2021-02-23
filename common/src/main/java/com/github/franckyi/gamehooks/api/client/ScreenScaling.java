package com.github.franckyi.gamehooks.api.client;

import com.github.franckyi.databindings.api.IntegerProperty;

public interface ScreenScaling {
    default int getScale() {
        return scaleProperty().getValue();
    }

    IntegerProperty scaleProperty();

    default void setScale(int value) {
        scaleProperty().setValue(value);
    }

    default void scaleUp() {
        if (getScale() >= getMaxScale()) {
            setScale(0);
        } else {
            setScale(getScale() + 1);
        }
    }

    default void scaleDown() {
        if (getScale() == 0) {
            setScale(getMaxScale());
        } else {
            setScale(getScale() - 1);
        }
    }

    default boolean canScaleUp() {
        return getScale() != 0;
    }

    default boolean canScaleDown() {
        return getScale() != 1;
    }

    int getMaxScale();
}
