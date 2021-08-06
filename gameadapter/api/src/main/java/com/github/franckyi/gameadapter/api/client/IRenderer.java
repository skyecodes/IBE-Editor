package com.github.franckyi.gameadapter.api.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.text.Text;

import java.util.List;

public interface IRenderer {
    static IRenderer get() {
        return Game.getClient().getRenderer();
    }

    int getFontHeight(Text text);

    int getFontWidth(Text text);

    void drawString(IMatrices matrices, Text text, float x, float y, int color, boolean shadow);

    void fillRectangle(IMatrices matrices, int x0, int y0, int x1, int y1, int color);

    void drawTexture(IMatrices matrices, IIdentifier id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight);

    void drawSprite(IMatrices matrices, ISprite sprite, int x, int y, int imageWidth, int imageHeight);

    void drawTooltip(IMatrices matrices, List<Text> text, int x, int y);

    void drawTooltip(IMatrices matrices, IItemStack itemStack, int x, int y);

    void drawItem(IItemStack itemStack, int x, int y);

    default void drawVLine(IMatrices matrices, int x, int y0, int y1, int color) {
        fillRectangle(matrices, x, y0, x + 1, y1, color);
    }

    default void drawHLine(IMatrices matrices, int y, int x0, int x1, int color) {
        fillRectangle(matrices, x0, y, x1, y + 1, color);
    }

    default void drawRectangle(IMatrices matrices, int x0, int y0, int x1, int y1, int color) {
        drawHLine(matrices, y0, x0, x1 - 1, color);
        drawVLine(matrices, x1 - 1, y0, y1 - 1, color);
        drawHLine(matrices, y1 - 1, x1, x0 + 1, color);
        drawVLine(matrices, x0, y1, y0 + 1, color);
    }
}
