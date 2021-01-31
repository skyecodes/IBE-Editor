package com.github.franckyi.guapi.common.event;

public class MouseButtonEvent extends MouseEvent {
    private final int button;

    public MouseButtonEvent(double mouseX, double mouseY, int button) {
        super(mouseX, mouseY);
        this.button = button;
    }

    public int getButton() {
        return button;
    }
}
