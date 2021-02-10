package com.github.franckyi.guapi.api.event;

import com.github.franckyi.guapi.impl.node.AbstractNode;

public interface MouseEvent extends ScreenEvent {
    double getMouseX();

    double getMouseY();

    AbstractNode getTarget();

    void setTarget(AbstractNode target);
}
