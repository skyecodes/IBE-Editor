package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;

public class ForgeFontRenderer implements FontRenderer<MatrixStack, ITextComponent> {
    public static final FontRenderer<?, ?> INSTANCE = new ForgeFontRenderer();

    private ForgeFontRenderer() {
    }

    private net.minecraft.client.gui.FontRenderer font() {
        return Minecraft.getInstance().fontRenderer;
    }

    @Override
    public int getFontHeight(ITextComponent text) {
        return font().FONT_HEIGHT;
    }

    @Override
    public int getFontWidth(ITextComponent text) {
        return font().getStringPropertyWidth(text);
    }

    @Override
    public void drawString(MatrixStack matrixStack, ITextComponent text, float x, float y, int color, boolean shadow) {
        if (shadow) {
            font().func_243246_a(matrixStack, text, x, y, color);
        } else {
            font().func_243248_b(matrixStack, text, x, y, color);
        }
    }

}
