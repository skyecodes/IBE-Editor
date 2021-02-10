package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.MouseDragEvent;

public class MouseDragEventImpl extends MouseButtonEventImpl implements MouseDragEvent {
    protected final double deltaX, deltaY;

    public MouseDragEventImpl(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        super(mouseX, mouseY, button);
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
