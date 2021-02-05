package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.guapi.util.Insets;

public interface Parent {
    int getWidth();

    int getHeight();

    Insets getPadding();

    void updateChildrenPos();

    ObservableValue<Scene> sceneProperty();

    ObservableValue<Boolean> disabledProperty();

    default void computeWidth() {
    }

    default void computeHeight() {
    }

    default int getMaxChildrenWidth() {
        return getWidth() - getPadding().getHorizontal();
    }

    default int getMaxChildrenHeight() {
        return getHeight() - getPadding().getVertical();
    }
}
