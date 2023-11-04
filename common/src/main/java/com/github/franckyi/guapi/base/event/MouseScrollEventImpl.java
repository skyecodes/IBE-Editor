package com.github.franckyi.guapi.base.event;

import com.github.franckyi.guapi.api.event.MouseScrollEvent;

public class MouseScrollEventImpl extends MouseEventImpl implements MouseScrollEvent {
    protected final double deltaX;
    protected final double deltaY;

    public MouseScrollEventImpl(double mouseX, double mouseY, double deltaX, double deltaY) {
        super(mouseX, mouseY);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    @Override
    public double getDeltaX() {
        return deltaX;
    }

    @Override
    public double getDeltaY() {
        return deltaY;
    }
}
