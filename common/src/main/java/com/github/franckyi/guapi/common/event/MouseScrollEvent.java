package com.github.franckyi.guapi.common.event;

public class MouseScrollEvent extends MouseEvent {
    private final double amount;

    public MouseScrollEvent(double mouseX, double mouseY, double amount) {
        super(mouseX, mouseY);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
