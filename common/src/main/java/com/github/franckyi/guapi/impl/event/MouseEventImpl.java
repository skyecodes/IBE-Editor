package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.impl.node.AbstractNode;

public class MouseEventImpl extends ScreenEventImpl implements MouseEvent {
    protected final double mouseX, mouseY;
    private AbstractNode target;

    public MouseEventImpl(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public double getMouseX() {
        return mouseX;
    }

    @Override
    public double getMouseY() {
        return mouseY;
    }

    @Override
    public AbstractNode getTarget() {
        return target;
    }

    @Override
    public void setTarget(AbstractNode target) {
        this.target = target;
    }
}
