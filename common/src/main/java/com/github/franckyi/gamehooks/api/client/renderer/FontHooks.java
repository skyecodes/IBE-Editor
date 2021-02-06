package com.github.franckyi.gamehooks.api.client.renderer;

public interface FontHooks<M, T> {
    int getFontHeight();

    int getFontWidth(String text);

    void drawString(M matrices, T text, float x, float y, int color, boolean shadow);

    String trimToWidth(String text, int maxWidth);
}
