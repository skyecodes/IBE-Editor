package com.github.franckyi.guapi.common.hooks;

public interface RenderContext<T> {
    T getMatrixStack();
    int getMouseX();
    int getMouseY();
    float getDelta();
}
