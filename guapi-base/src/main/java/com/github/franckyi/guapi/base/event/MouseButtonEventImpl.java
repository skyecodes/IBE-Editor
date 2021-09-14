package com.github.franckyi.guapi.base.event;

import com.github.franckyi.guapi.api.event.MouseButtonEvent;

public class MouseButtonEventImpl extends MouseEventImpl implements MouseButtonEvent {
    protected final int button;

    public MouseButtonEventImpl(double mouseX, double mouseY, int button) {
        super(mouseX, mouseY);
        this.button = button;
    }

    @Override
    public int getButton() {
        return button;
    }
}
