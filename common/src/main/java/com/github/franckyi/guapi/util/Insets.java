package com.github.franckyi.guapi.util;

import java.util.Objects;

public final class Insets {
    public static final Insets NONE = new Insets(0);
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

    public int getHorizontal() {
        return getLeft() + getRight();
    }

    public int getVertical() {
        return getTop() + getBottom();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insets insets = (Insets) o;
        return top == insets.top && right == insets.right && bottom == insets.bottom && left == insets.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, right, bottom, left);
    }

    @Override
    public String toString() {
        return "Insets{" +
                "top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                ", left=" + left +
                '}';
    }
}
