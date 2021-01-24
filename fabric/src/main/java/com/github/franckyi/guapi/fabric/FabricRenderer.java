package com.github.franckyi.guapi.fabric;

import com.github.franckyi.guapi.common.hooks.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.function.Supplier;

public class FabricRenderer implements Renderer<MatrixStack> {
    private static final Supplier<MinecraftClient> mc = MinecraftClient::getInstance;

    @Override
    public Font<MatrixStack> font() {
        return new Font<MatrixStack>() {
            private final TextRenderer font = mc.get().textRenderer;
            @Override
            public int getFontHeight() {
                return font.fontHeight;
            }

            @Override
            public int getFontWidth(String text) {
                return font.getWidth(text);
            }

            @Override
            public void drawString(MatrixStack matrixStack, String text, float x, float y, int color) {
                font.draw(matrixStack, text, x, y, color);
            }
        };
    }
}
