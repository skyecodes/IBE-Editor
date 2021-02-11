package com.github.franckyi.guapi.api;

public interface Renderable<M> {
    default boolean preRender(M matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(M matrices, int mouseX, int mouseY, float delta);

    default void postRender(M matrices, int mouseX, int mouseY, float delta) {
    }
}
