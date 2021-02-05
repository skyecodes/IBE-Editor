package com.github.franckyi.guapi.util;

public abstract class Color {
    private final int value;

    protected Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    protected void checkValues(int r, int g, int b, int a) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 255) {
            throw new IllegalArgumentException("RGBA values must be between 0 and 255");
        }
    }

    public static class Text extends Color {
        public Text(int r, int g, int b) {
            super(((r & 0xff) << 16) |
                    ((g & 0xff) << 8) |
                    ((b & 0xff)));
            checkValues(r, g, b, 255);
        }
    }

    public static class Background extends Color {
        public Background(int r, int g, int b) {
            this(r, g, b, 0xff);
        }

        public Background(int r, int g, int b, int a) {
            super(((a & 0xff) << 24) |
                    ((r & 0xff) << 16) |
                    ((g & 0xff) << 8) |
                    ((b & 0xff)));
            checkValues(r, g, b, a);
        }
    }
}
