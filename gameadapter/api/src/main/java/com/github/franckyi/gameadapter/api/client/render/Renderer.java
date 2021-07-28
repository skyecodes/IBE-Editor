package com.github.franckyi.gameadapter.api.client.render;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Item;

import java.util.List;

public interface Renderer {
    int getFontHeight(Text text);

    int getFontWidth(Text text);

    void drawString(Matrices matrices, Text text, float x, float y, int color, boolean shadow);

    void fillRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color);

    void drawTexture(Matrices matrices, String id, int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight);

    void drawTooltip(Matrices matrices, List<Text> text, int x, int y);

    void drawTooltip(Matrices matrices, Item item, int x, int y);

    void drawItem(Item item, int x, int y);

    default void drawVLine(Matrices matrices, int x, int y0, int y1, int color) {
        fillRectangle(matrices, x, y0, x + 1, y1, color);
    }

    default void drawHLine(Matrices matrices, int y, int x0, int x1, int color) {
        fillRectangle(matrices, x0, y, x1, y + 1, color);
    }

    default void drawRectangle(Matrices matrices, int x0, int y0, int x1, int y1, int color) {
        drawHLine(matrices, y0, x0, x1 - 1, color);
        drawVLine(matrices, x1 - 1, y0, y1 - 1, color);
        drawHLine(matrices, y1 - 1, x1, x0 + 1, color);
        drawVLine(matrices, x0, y1, y0 + 1, color);
    }
}
