package com.github.franckyi.guapi.hooks.api.theme;

import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.util.HookUtil;

public interface DelegatedRenderer<M> extends EventTarget, HookUtil {
    default boolean preRender() {
        return false;
    }

    void render(M matrices, int mouseX, int mouseY, float delta);
}
