package com.github.franckyi.guapi.hooks.api;

public interface RenderContext<T> {
    T getMatrixStack();
    int getMouseX();
    int getMouseY();
    float getDelta();
}
