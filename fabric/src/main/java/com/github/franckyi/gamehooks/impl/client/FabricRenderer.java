package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Renderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.util.math.MatrixStack;

public final class FabricRenderer implements Renderer<MatrixStack> {
    public static final Renderer<?> INSTANCE = new FabricRenderer();

    private FabricRenderer() {
    }

    private MinecraftClient mc() {
        return MinecraftClient.getInstance();
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
        private TextRenderer font() {
            return mc().textRenderer;
        }

        @Override
        public int getFontHeight() {
            return font().fontHeight;
        }

        @Override
        public int getFontWidth(String text) {
            return font().getWidth(text);
        }

        @Override
        public void drawString(MatrixStack matrices, String text, float x, float y, int color, boolean shadow) {
            if (shadow) {
                font().drawWithShadow(matrices, text, x, y, color);
            } else {
                font().draw(matrices, text, x, y, color);
            }
        }

        @Override
        public String trimToWidth(String text, int maxWidth) {
            return font().trimToWidth(text, maxWidth);
        }
    };

    private final Shape<MatrixStack> shape = new Shape<MatrixStack>() {
        @Override
        public void fillRectangle(MatrixStack matrices, int x0, int y0, int x1, int y1, int color) {
            DrawableHelper.fill(matrices, x0, y0, x1, y1, color);
        }
    };

    private final Widget<MatrixStack> widget = new Widget<MatrixStack>() {
        @Override
        public void bindTexture() {
            mc().getTextureManager().bindTexture(AbstractButtonWidget.WIDGETS_LOCATION);
        }

        @Override
        public void drawTexture(MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
            AbstractButtonWidget.drawTexture(matrices, x, y, 0, (float) u, (float) v, width, height, 256, 256);
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
