package com.github.franckyi.guapi.common.event;

public class MouseDragEvent extends MouseButtonEvent {
    private final double deltaX, deltaY;

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
}
