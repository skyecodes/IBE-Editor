package com.github.franckyi.guapi.forge;

import com.github.franckyi.guapi.common.hooks.Renderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.function.Supplier;

public class ForgeRenderer implements Renderer<MatrixStack> {
    private static final Supplier<Minecraft> mc = Minecraft::getInstance;

    @Override
    public Font<MatrixStack> font() {
        return new Font<MatrixStack>() {
            private final FontRenderer font = mc.get().fontRenderer;
            @Override
            public int getFontHeight() {
                return font.FONT_HEIGHT;
            }

            @Override
            public int getFontWidth(String text) {
                return font.getStringWidth(text);
            }

            @Override
            public void drawString(MatrixStack matrixStack, String text, float x, float y, int color) {
                font.drawString(matrixStack, text, x, y, color);
            }
        };
    }
}
