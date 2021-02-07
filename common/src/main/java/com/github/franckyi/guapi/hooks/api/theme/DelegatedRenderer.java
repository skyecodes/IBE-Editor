package com.github.franckyi.guapi.hooks.api.theme;

import com.github.franckyi.guapi.EventTarget;
import com.github.franckyi.guapi.hooks.api.RenderContext;

public interface DelegatedRenderer<M> extends EventTarget {
    void render(RenderContext<M> ctx);
}
