package com.github.franckyi.guapi.hooks.api;

public interface RenderContext<M> {
    M getMatrices();

    int getMouseX();

    int getMouseY();

    float getDelta();

    static <M> RenderContext<M> of(M matrices, int mouseX, int mouseY, float delta) {
        return new RenderContext<M>() {
            @Override
            public M getMatrices() {
                return matrices;
            }

            @Override
            public int getMouseX() {
                return mouseX;
            }

            @Override
            public int getMouseY() {
                return mouseY;
            }

            @Override
            public float getDelta() {
                return delta;
            }
        };
    }
}
