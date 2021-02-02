package com.github.franckyi.guapi.common.util;

public enum Align {
    TOP_LEFT(Vertical.TOP, Horizontal.LEFT),
    TOP_CENTER(Vertical.TOP, Horizontal.CENTER),
    TOP_RIGHT(Vertical.TOP, Horizontal.RIGHT),
    CENTER_LEFT(Vertical.CENTER, Horizontal.LEFT),
    CENTER(Vertical.CENTER, Horizontal.CENTER),
    CENTER_RIGHT(Vertical.CENTER, Horizontal.RIGHT),
    BOTTOM_LEFT(Vertical.BOTTOM, Horizontal.LEFT),
    BOTTOM_CENTER(Vertical.BOTTOM, Horizontal.CENTER),
    BOTTOM_RIGHT(Vertical.BOTTOM, Horizontal.RIGHT);

    private final Vertical verticalAlign;
    private final Horizontal horizontalAlign;

    Align(Vertical verticalAlign, Horizontal horizontalAlign) {
        this.verticalAlign = verticalAlign;
        this.horizontalAlign = horizontalAlign;
    }

    public Vertical getVerticalAlign() {
        return verticalAlign;
    }

    public Horizontal getHorizontalAlign() {
        return horizontalAlign;
    }

    public enum Horizontal {
        LEFT, CENTER, RIGHT
    }

    public enum Vertical {
        TOP, CENTER, BOTTOM
    }
}
