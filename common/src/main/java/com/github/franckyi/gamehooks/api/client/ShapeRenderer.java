package com.github.franckyi.gamehooks.api.client;

public interface ShapeRenderer<M> {
    void fillRectangle(M matrices, int x0, int y0, int x1, int y1, int color);

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
