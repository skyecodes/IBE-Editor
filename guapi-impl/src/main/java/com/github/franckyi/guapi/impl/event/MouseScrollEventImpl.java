package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.MouseScrollEvent;

public class MouseScrollEventImpl extends MouseEventImpl implements MouseScrollEvent {
    protected final double amount;

    public MouseScrollEventImpl(double mouseX, double mouseY, double amount) {
        super(mouseX, mouseY);
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
