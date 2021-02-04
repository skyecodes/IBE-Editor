package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public final class FabricRenderer implements Renderer<MatrixStack> {
    public static final FabricRenderer INSTANCE = new FabricRenderer();

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
}
