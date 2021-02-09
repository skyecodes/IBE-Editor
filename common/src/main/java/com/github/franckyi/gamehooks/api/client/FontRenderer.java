package com.github.franckyi.gamehooks.api.client;

public interface FontRenderer<M, T> {
    int getFontHeight(T text);

    int getFontWidth(T text);

    void drawString(M matrices, T text, float x, float y, int color, boolean shadow);
}
