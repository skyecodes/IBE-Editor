package com.github.franckyi.minecraft.impl.client.screen;

import com.github.franckyi.guapi.impl.AbstractScreenHandler;
import com.github.franckyi.minecraft.api.client.screen.ScreenHandler;
import com.github.franckyi.minecraft.impl.client.render.ForgeMatrices;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public final class ForgeScreenHandler extends AbstractScreenHandler {
    public static final ScreenHandler INSTANCE = new ForgeScreenHandler();
    private final Screen screen = new ScreenImpl();
    private Screen oldScreen;

    private ForgeScreenHandler() {
    }

    @Override
    protected void openScreen() {
        oldScreen = Minecraft.getInstance().currentScreen;
        Minecraft.getInstance().displayGuiScreen(screen);
    }

    @Override
    protected void closeScreen() {
        Minecraft.getInstance().displayGuiScreen(oldScreen);
    }

    private final class ScreenImpl extends Screen {
        protected ScreenImpl() {
            super(new StringTextComponent(""));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
            if (getCurrentScene() == null) return;
            if (getCurrentScene().isTexturedBackground()) {
                renderDirtBackground(0);
            } else {
                renderBackground(matrices);
            }
            ForgeScreenHandler.this.render(ForgeMatrices.of(matrices), mouseX, mouseY, partialTicks);
        }

        @Override
        public void tick() {
            ForgeScreenHandler.this.tick();
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