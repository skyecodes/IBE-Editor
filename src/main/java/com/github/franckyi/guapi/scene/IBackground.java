package com.github.franckyi.guapi.scene;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;

@FunctionalInterface
public interface IBackground {

    IBackground DEFAULT = Screen::renderBackground;
    IBackground NONE = (screen, matrixStack) -> {
    };

    static IBackground texturedBackground(int tint) {
        return (screen, matrixStack) -> screen.renderDirtBackground(tint);
    }

    static IBackground worldBackground(int tint) {
        return (screen, matrixStack) -> screen.renderBackground(matrixStack, tint);
    }

    void draw(Screen screen, MatrixStack matrixStack);

}
