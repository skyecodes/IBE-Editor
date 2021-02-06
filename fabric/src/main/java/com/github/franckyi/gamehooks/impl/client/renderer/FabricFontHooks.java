package com.github.franckyi.gamehooks.impl.client.renderer;

import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class FabricFontHooks implements FontHooks<MatrixStack, Text> {
    public static final FontHooks<?, ?> INSTANCE = new FabricFontHooks();

    private FabricFontHooks() {
    }

    private TextRenderer font() {
        return MinecraftClient.getInstance().textRenderer;
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
    public void drawString(MatrixStack matrices, Text text, float x, float y, int color, boolean shadow) {
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
}
