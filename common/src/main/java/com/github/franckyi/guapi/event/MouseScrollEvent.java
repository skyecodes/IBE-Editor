package com.github.franckyi.guapi.event;

public class MouseScrollEvent extends MouseEvent {
    protected final double amount;

    public MouseScrollEvent(double mouseX, double mouseY, double amount) {
        super(mouseX, mouseY);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "MouseScrollEvent{" +
                "mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", amount=" + amount +
                ", consumed=" + consumed +
                '}';
    }
}
