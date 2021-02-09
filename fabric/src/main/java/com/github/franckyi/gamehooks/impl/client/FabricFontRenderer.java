package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.FontRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class FabricFontRenderer implements FontRenderer<MatrixStack, Text> {
    public static final FontRenderer<?, ?> INSTANCE = new FabricFontRenderer();

    private FabricFontRenderer() {
    }

    private TextRenderer font() {
        return MinecraftClient.getInstance().textRenderer;
    }

    @Override
    public int getFontHeight(Text text) {
        return font().fontHeight;
    }

    @Override
    public int getFontWidth(Text text) {
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
}
