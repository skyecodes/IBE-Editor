package com.github.franckyi.guapi.event;

import com.github.franckyi.guapi.node.Node;

public class MouseEvent extends ScreenEvent {
    protected final double mouseX, mouseY;
    private Node target;

    public MouseEvent(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "MouseEvent{" +
                "target=" + target +
                ", mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", consumed=" + consumed +
                '}';
    }
}
