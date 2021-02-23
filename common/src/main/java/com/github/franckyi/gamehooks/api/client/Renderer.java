package com.github.franckyi.gamehooks.api.client;

import com.github.franckyi.gamehooks.util.common.text.Text;

public interface Renderer<M, T> {
    int getFontHeight(T text);

    int getFontWidth(T text);

    void drawString(M matrices, T text, float x, float y, int color, boolean shadow);

    void fillRectangle(M matrices, int x0, int y0, int x1, int y1, int color);

    void drawTexture(M matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight);

    void drawTooltip(M matrices, Text text, int x, int y);

    default void drawVLine(M matrices, int x, int y0, int y1, int color) {
        fillRectangle(matrices, x, y0, x + 1, y1, color);
    }

    default void drawHLine(M matrices, int y, int x0, int x1, int color) {
        fillRectangle(matrices, x0, y, x1, y + 1, color);
    }

    default void drawRectangle(M matrices, int x0, int y0, int x1, int y1, int color) {
        drawHLine(matrices, y0, x0, x1 - 1, color);
        drawVLine(matrices, x1 - 1, y0, y1 - 1, color);
        drawHLine(matrices, y1 - 1, x1, x0 + 1, color);
        drawVLine(matrices, x0, y1, y0 + 1, color);
    }
}
