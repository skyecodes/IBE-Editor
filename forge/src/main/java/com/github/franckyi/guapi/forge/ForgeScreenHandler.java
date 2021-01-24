package com.github.franckyi.guapi.forge;

import com.github.franckyi.guapi.common.hooks.RenderContext;
import com.github.franckyi.guapi.common.hooks.ScreenHandlerBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import java.util.function.Consumer;

public class ForgeScreenHandler extends ScreenHandlerBase<Screen> {
    public ForgeScreenHandler() {
        initScreen(new ScreenImpl());
    }

    @Override
    protected Consumer<Screen> getMinecraftScreenHandler() {
        return Minecraft.getInstance()::displayGuiScreen;
    }

    private class ScreenImpl extends Screen {
        protected ScreenImpl() {
            super(new StringTextComponent(""));
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
