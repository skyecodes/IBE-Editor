package com.github.franckyi.guapi.api.theme;

import com.github.franckyi.guapi.api.EventTarget;

public interface DelegatedRenderer<M> extends EventTarget {
    default boolean preRender() {
        return false;
    }

    void render(M matrices, int mouseX, int mouseY, float delta);
}
