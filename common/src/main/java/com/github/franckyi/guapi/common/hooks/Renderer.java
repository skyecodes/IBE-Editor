package com.github.franckyi.guapi.common.hooks;

public interface Renderer<T> {
    Font<T> font();

    interface Font<T> {
        int getFontHeight();
        int getFontWidth(String text);
        void drawString(T matrixStack, String text, float x, float y, int color);
    }
}
