package com.github.franckyi.guapi.math;

import com.google.common.base.Objects;

public final class Insets {

    public static final Insets NONE = new Insets(0);

    private final int top;
    private final int right;
    private final int bottom;
    private final int left;

    public Insets(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Insets(int vertical, int horizontal) {
        this(vertical, horizontal, vertical, horizontal);
    }

    public Insets(int value) {
        this(value, value);
    }

    public static Insets top(int top) {
        return new Insets(top, 0, 0, 0);
    }

    public static Insets right(int right) {
        return new Insets(0, right, 0, 0);
    }

    public static Insets bottom(int bottom) {
        return new Insets(0, 0, bottom, 0);
    }

    public static Insets left(int left) {
        return new Insets(0, 0, 0, left);
    }

    public static Insets vertial(int vertical) {
        return new Insets(vertical, 0);
    }

    public static Insets horizontal(int horizontal) {
        return new Insets(0, horizontal);
    }

    public Insets add(int top, int right, int bottom, int left) {
        return new Insets(this.top + top, this.right + right, this.bottom + bottom, this.left + left);
    }

    public Insets addTop(int top) {
        return new Insets(this.top + top, right, bottom, left);
    }

    public Insets addRight(int right) {
        return new Insets(top, this.right + right, bottom, left);
    }

    public Insets addBottom(int bottom) {
        return new Insets(top + top, right, this.bottom + bottom, left);
    }

    public Insets addLeft(int left) {
        return new Insets(top + top, right, bottom, this.left + left);
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
        return this.getLeft() + this.getRight();
    }

    public int getVertical() {
        return this.getTop() + this.getBottom();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Insets padding = (Insets) o;
        return Double.compare(padding.getTop(), this.getTop()) == 0 &&
                Double.compare(padding.getRight(), this.getRight()) == 0 &&
                Double.compare(padding.getBottom(), this.getBottom()) == 0 &&
                Double.compare(padding.getLeft(), this.getLeft()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTop(), getRight(), getBottom(), getLeft());
    }
}
