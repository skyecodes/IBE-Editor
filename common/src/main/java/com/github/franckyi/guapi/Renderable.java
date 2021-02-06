package com.github.franckyi.guapi;

import com.github.franckyi.guapi.hooks.api.RenderContext;

public interface Renderable {
    void render(RenderContext<?> ctx);
}
