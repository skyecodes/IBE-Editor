package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObservableValue;

public interface Parent {
    ObservableValue<Scene> sceneProperty();

    ObservableValue<Boolean> disabledProperty();

    int getMaxChildrenWidth();

    int getMaxChildrenHeight();

    void shouldComputeSize();

    void shouldUpdateChildren();
}
