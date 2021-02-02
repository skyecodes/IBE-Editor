package com.github.franckyi.guapi.common.event;

public class MouseButtonEvent extends MouseEvent {
    protected final int button;

    public MouseButtonEvent(double mouseX, double mouseY, int button) {
        super(mouseX, mouseY);
        this.button = button;
    }

    public int getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "MouseButtonEvent{" +
                "button=" + button +
                ", mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", consumed=" + consumed +
                '}';
    }
}
