package com.github.franckyi.guapi.common.hooks;

public interface Renderer<T> {
    Font<T> font();

    Shape<T> shape();

    interface Font<T> {
        int getFontHeight();

        int getFontWidth(String text);

        void drawString(T matrices, String text, float x, float y, int color, boolean shadow);

        String trimToWidth(String text, int maxWidth);
    }

    interface Shape<T> {
        void fillRectangle(T matrices, int x0, int y0, int x1, int y1, int color);

        default void drawHLine(T matrices, int x, int y0, int y1, int color) {
            fillRectangle(matrices, x, y0, x + 1, y1, color);
        }

        default void drawVLine(T matrices, int y, int x0, int x1, int color) {
            fillRectangle(matrices, x0, y, x1, y + 1, color);
        }

        default void drawRectangle(T matrices, int x0, int y0, int x1, int y1, int color) {
            drawHLine(matrices, x0, y0, y1, color);
            drawVLine(matrices, y1 - 1, x0, x1, color);
            drawHLine(matrices, x1 - 1, y0, y1, color);
            drawVLine(matrices, y0, x0, x1, color);
        }
    }
}
