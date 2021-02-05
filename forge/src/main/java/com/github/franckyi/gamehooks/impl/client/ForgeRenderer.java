package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Renderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;

public final class ForgeRenderer extends AbstractGui implements Renderer<MatrixStack> {
    public static final Renderer<?> INSTANCE = new ForgeRenderer();

    private ForgeRenderer() {
    }

    private Minecraft mc() {
        return Minecraft.getInstance();
    }

    @Override
    public Font<MatrixStack> font() {
        return font;
    }

    @Override
    public Shape<MatrixStack> shape() {
        return shape;
    }

    @Override
    public Widget<MatrixStack> widget() {
        return widget;
    }

    @Override
    public System system() {
        return system;
    }

    private final Font<MatrixStack> font = new Font<MatrixStack>() {
        private FontRenderer font() {
            return mc().fontRenderer;
        }

        @Override
        public int getFontHeight() {
            return font().FONT_HEIGHT;
        }

        @Override
        public int getFontWidth(String text) {
            return font().getStringWidth(text);
        }

        @Override
        public void drawString(MatrixStack matrixStack, String text, float x, float y, int color, boolean shadow) {
            if (shadow) {
                font().drawStringWithShadow(matrixStack, text, x, y, color);
            } else {
                font().drawString(matrixStack, text, x, y, color);
            }
        }

        @Override
        public String trimToWidth(String text, int maxWidth) {
            return font().func_238412_a_(text, maxWidth);
        }
    };

    private final Shape<MatrixStack> shape = new Shape<MatrixStack>() {
        @Override
        public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
            fill(matrices, x0, y0, x1, y1, color);
        }
    };

    private final Widget<MatrixStack> widget = new Widget<MatrixStack>() {
        @Override
        public void bindTexture() {
            mc().getTextureManager().bindTexture(net.minecraft.client.gui.widget.Widget.WIDGETS_LOCATION);
        }

        @Override
        public void drawTexture(MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
            net.minecraft.client.gui.widget.Widget.blit(matrices, x, y, 0, (float) u, (float) v, width, height, 256, 256);
        }
    };

    private final System system = new System() {
        @Override
        public void color4f(float red, float green, float blue, float alpha) {
            RenderSystem.color4f(red, green, blue, alpha);
        }

        @Override
        public void enableBlend() {
            RenderSystem.enableBlend();
        }

        @Override
        public void defaultBlendFunc() {
            RenderSystem.defaultBlendFunc();
        }

        @Override
        public void enableDepthTest() {
            RenderSystem.enableDepthTest();
        }
    };
}
