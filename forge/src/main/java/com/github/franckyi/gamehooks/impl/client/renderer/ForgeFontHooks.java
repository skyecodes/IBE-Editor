package com.github.franckyi.gamehooks.impl.client.renderer;

import com.github.franckyi.gamehooks.api.client.renderer.FontHooks;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;

public class ForgeFontHooks implements FontHooks<MatrixStack, ITextComponent> {
    public static final FontHooks<?, ?> INSTANCE = new ForgeFontHooks();

    private ForgeFontHooks() {
    }

    private FontRenderer font() {
        return Minecraft.getInstance().fontRenderer;
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
    public void drawString(MatrixStack matrixStack, ITextComponent text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().func_243246_a(matrixStack, text, x, y, color);
        } else {
            font().func_243248_b(matrixStack, text, x, y, color);
        }
    }

    @Override
    public String trimToWidth(String text, int maxWidth) {
        return font().func_238412_a_(text, maxWidth);
    }
}
