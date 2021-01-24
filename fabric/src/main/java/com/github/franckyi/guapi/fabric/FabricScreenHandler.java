package com.github.franckyi.guapi.fabric;

import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.hooks.ScreenHandlerBase;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.function.Consumer;

public class FabricScreenHandler extends ScreenHandlerBase<Screen> {
    public FabricScreenHandler() {
        initScreen(new ScreenImpl());
    }

    @Override
    protected Consumer<Screen> getMinecraftScreenHandler() {
        return MinecraftClient.getInstance()::openScreen;
    }

    private class ScreenImpl extends Screen {
        protected ScreenImpl() {
            super(new LiteralText(""));
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (keyCode == 256 && shouldCloseOnEsc()) {
                hide();
                return true;
            }
            return false;
        }

        @Override
        public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
            if (getCurrentScene() == null) return;
            getCurrentScene().render(new RenderContext<MatrixStack>() {
                @Override
                public MatrixStack getMatrixStack() {
                    return matrixStack;
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
                    return partialTicks;
                }
            });
        }
    }
}
