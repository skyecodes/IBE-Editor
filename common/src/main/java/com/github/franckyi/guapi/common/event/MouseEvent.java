package com.github.franckyi.guapi.common.event;

public class MouseEvent extends ScreenEvent {
    private final double mouseX, mouseY;

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
}
