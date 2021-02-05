package com.github.franckyi.guapi.hooks.impl;

import com.github.franckyi.guapi.hooks.api.RenderContext;
import com.github.franckyi.guapi.hooks.api.ScreenHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

import java.util.function.Consumer;

public final class ForgeScreenHandler extends AbstractScreenHandler<Screen> {
    public static final ScreenHandler INSTANCE = new ForgeScreenHandler();

    private ForgeScreenHandler() {
        initScreen(new ScreenImpl());
    }

    @Override
    protected Consumer<Screen> getMinecraftScreenHandler() {
        return Minecraft.getInstance()::displayGuiScreen;
    }

    private final class ScreenImpl extends Screen {
        protected ScreenImpl() {
            super(new StringTextComponent(""));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
            if (getCurrentScene() == null) return;
            getCurrentScene().render(new RenderContext<MatrixStack>() {
                @Override
                public MatrixStack getMatrices() {
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
                    return partialTicks;
                }
            });
        }

        @Override
        public void init(Minecraft client, int width, int height) {
            super.init(client, width, height);
            ForgeScreenHandler.this.updateSize(width, height);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            return ForgeScreenHandler.this.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseReleased(double mouseX, double mouseY, int button) {
            return ForgeScreenHandler.this.mouseReleased(mouseX, mouseY, button);
        }

        @Override
        public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
            return ForgeScreenHandler.this.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }

        @Override
        public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
            return ForgeScreenHandler.this.mouseScrolled(mouseX, mouseY, amount);
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            return ForgeScreenHandler.this.keyPressed(keyCode, scanCode, modifiers);
        }

        @Override
        public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
            return ForgeScreenHandler.this.keyReleased(keyCode, scanCode, modifiers);
        }

        @Override
        public boolean charTyped(char chr, int modifiers) {
            return ForgeScreenHandler.this.charTyped(chr, modifiers);
        }

        @Override
        public void mouseMoved(double mouseX, double mouseY) {
            ForgeScreenHandler.this.mouseMoved(mouseX, mouseY);
        }
    }
}
