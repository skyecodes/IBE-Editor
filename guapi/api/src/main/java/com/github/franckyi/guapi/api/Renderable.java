package com.github.franckyi.guapi.api;

import com.github.franckyi.gameadapter.api.client.IMatrices;

public interface Renderable {
    default boolean preRender(IMatrices matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(IMatrices matrices, int mouseX, int mouseY, float delta);

    default void postRender(IMatrices matrices, int mouseX, int mouseY, float delta) {
    }
}
