package com.github.franckyi.guapi.api;

import com.mojang.blaze3d.vertex.PoseStack;

public interface Renderable {
    default boolean preRender(PoseStack matrices, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(PoseStack matrices, int mouseX, int mouseY, float delta);

    default void postRender(PoseStack matrices, int mouseX, int mouseY, float delta) {
    }
}
