package com.github.franckyi.guapi.hooks.api.theme;

import com.github.franckyi.guapi.EventTarget;

public interface DelegatedRenderer<M> extends EventTarget {
    void render(M matrices, int mouseX, int mouseY, float delta);
}
