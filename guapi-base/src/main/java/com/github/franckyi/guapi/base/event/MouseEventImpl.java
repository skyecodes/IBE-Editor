package com.github.franckyi.guapi.base.event;

import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.node.Node;

public class MouseEventImpl extends ScreenEventImpl implements MouseEvent {
    protected final double mouseX, mouseY;
    private Node target;

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
    public Node getTarget() {
        return target;
    }

    @Override
    public void setTarget(Node target) {
        this.target = target;
    }
}
