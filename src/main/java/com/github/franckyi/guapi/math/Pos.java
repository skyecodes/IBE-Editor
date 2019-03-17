package com.github.franckyi.guapi.math;

import com.github.franckyi.guapi.Node;

import java.util.function.Function;

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

    public int getStartX(Node node) {
        return horizontalPos.startX.apply(node);
    }

    public int getStartY(Node node) {
        return verticalPos.startY.apply(node);
    }

    public enum Horizontal {

        LEFT(Node::getX),
        CENTER(node -> node.getX() + (node.getWidth() - node.getComputedWidth()) / 2),
        RIGHT(node -> node.getX() + node.getWidth() - node.getComputedWidth());

        private final Function<Node, Integer> startX;

        Horizontal(Function<Node, Integer> startX) {
            this.startX = startX;
        }

    }

    public enum Vertical {

        TOP(Node::getY),
        CENTER(node -> node.getY() + (node.getHeight() - node.getComputedHeight()) / 2),
        BOTTOM(node -> node.getY() + node.getHeight() - node.getComputedHeight());

        private final Function<Node, Integer> startY;

        Vertical(Function<Node, Integer> startY) {
            this.startY = startY;
        }

    }

}

