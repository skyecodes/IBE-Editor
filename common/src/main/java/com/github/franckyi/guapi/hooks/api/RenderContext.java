package com.github.franckyi.guapi.hooks.api;

public interface RenderContext<M> {
    M getMatrices();

    int getMouseX();

    int getMouseY();

    float getDelta();
}
