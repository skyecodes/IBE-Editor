package com.github.franckyi.guapi.api;

public final class Color {
    public static int fromHex(String hex) {
        hex = hex.substring(1);
        int red = Integer.valueOf(hex.substring(0, 2), 16);
        int green = Integer.valueOf(hex.substring(2, 4), 16);
        int blue = Integer.valueOf(hex.substring(4, 6), 16);
        return fromRGB(red, green, blue);
    }

    public static int getAlpha(int color) {
        return (color >> 24) & 0xff;
    }

    public static int getRed(int color) {
        return (color >> 16) & 0xff;
    }

    public static int getGreen(int color) {
        return (color >> 8) & 0xff;
    }

    public static int getBlue(int color) {
        return (color) & 0xff;
    }

    public static int fromRGB(int r, int g, int b) {
        return fromRGBA(r, g, b, 255);
    }

    public static int fromRGBA(int r, int g, int b, int a) {
        checkValues(r, g, b, a);
        return (a & 0xff) << 24 |
                (r & 0xff) << 16 |
                (g & 0xff) << 8 |
                b & 0xff;
    }

    public static int fromRGB(double r, double g, double b) {
        return fromRGB((int) (r * 255), (int) (g * 255), (int) (b * 255));
    }

    public static int fromRGBA(double r, double g, double b, double a) {
        return fromRGBA((int) (r * 255), (int) (g * 255), (int) (b * 255), (int) (a * 255));
    }

    private static void checkValues(int r, int g, int b, int a) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 255) {
            throw new IllegalArgumentException("RGBA values must be between 0 and 255");
        }
    }

    public static final String BLACK = "black";
    public static final String DARK_BLUE = "dark_blue";
    public static final String DARK_GREEN = "dark_green";
    public static final String DARK_AQUA = "dark_aqua";
    public static final String DARK_RED = "dark_red";
    public static final String DARK_PURPLE = "dark_purple";
    public static final String GOLD = "gold";
    public static final String GRAY = "gray";
    public static final String DARK_GRAY = "dark_gray";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";
    public static final String AQUA = "aqua";
    public static final String RED = "red";
    public static final String LIGHT_PURPLE = "light_purple";
    public static final String YELLOW = "yellow";
    public static final String WHITE = "white";
    public static final int NONE = Integer.MIN_VALUE;
}
