package com.github.franckyi.guapi.common.math;

public final class Insets {
    private final int top, right, bottom, left;

    public Insets(int topRightBottomLeft) {
        this(topRightBottomLeft, topRightBottomLeft);
    }

    public Insets(int topBottom, int rightLeft) {
        this(topBottom, rightLeft, topBottom);
    }

    public Insets(int top, int rightLeft, int bottom) {
        this(top, rightLeft, bottom, rightLeft);
    }

    public Insets(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }
}
