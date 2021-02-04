package com.github.franckyi.guapi.event;

public class MouseButtonEvent extends MouseEvent {
    public static final int LEFT_BUTTON = 0;
    public static final int RIGHT_BUTTON = 1;
    public static final int MIDDLE_BUTTON = 2;
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
