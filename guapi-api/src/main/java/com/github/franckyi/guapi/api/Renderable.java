package com.github.franckyi.guapi.api;

import com.github.franckyi.minecraft.api.client.render.Matrices;

public interface Renderable {
    default boolean preRender(Matrices matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(Matrices matrices, int mouseX, int mouseY, float delta);

    default void postRender(Matrices matrices, int mouseX, int mouseY, float delta) {
    }
}
