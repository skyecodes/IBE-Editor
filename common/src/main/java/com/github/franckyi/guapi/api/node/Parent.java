package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableValue;

public interface Parent {
    ObservableValue<Scene> sceneProperty();

    ObservableBooleanValue disabledProperty();

    int getMaxChildrenWidth();

    int getMaxChildrenHeight();

    void shouldComputeSize();

    void shouldUpdateChildren();
}
