package com.github.franckyi.guapi.math;

public enum Pos {

    TOP_LEFT(Horizontal.LEFT, Vertical.TOP),
    TOP(Horizontal.CENTER, Vertical.TOP),
    TOP_RIGHT(Horizontal.RIGHT, Vertical.TOP),
    LEFT(Horizontal.LEFT, Vertical.CENTER),
    CENTER(Horizontal.CENTER, Vertical.CENTER),
    RIGHT(Horizontal.RIGHT, Vertical.CENTER),
    BOTTOM_LEFT(Horizontal.LEFT, Vertical.BOTTOM),
    BOTTOM(Horizontal.CENTER, Vertical.BOTTOM),
    BOTTOM_RIGHT(Horizontal.RIGHT, Vertical.BOTTOM);

    private final Horizontal horizontalPos;
    private final Vertical verticalPos;

    Pos(Horizontal horizontalPos, Vertical verticalPos) {
        this.horizontalPos = horizontalPos;
        this.verticalPos = verticalPos;
    }

    public Horizontal getHorizontalPos() {
        return horizontalPos;
    }

    public Vertical getVerticalPos() {
        return verticalPos;
    }

    public enum Horizontal {
        LEFT, CENTER, RIGHT
    }

    public enum Vertical {
        TOP, CENTER, BOTTOM
    }

}

