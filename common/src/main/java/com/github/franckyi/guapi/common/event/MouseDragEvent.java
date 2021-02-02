package com.github.franckyi.guapi.common.event;

public class MouseDragEvent extends MouseButtonEvent {
    protected final double deltaX, deltaY;

    public MouseDragEvent(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        super(mouseX, mouseY, button);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    @Override
    public String toString() {
        return "MouseDragEvent{" +
                "button=" + button +
                ", deltaY=" + deltaY +
                ", mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", consumed=" + consumed +
                '}';
    }
}
