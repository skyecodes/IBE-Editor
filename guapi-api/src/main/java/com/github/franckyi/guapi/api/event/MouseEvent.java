package com.github.franckyi.guapi.api.event;

import com.github.franckyi.guapi.api.node.Node;

public interface MouseEvent extends ScreenEvent {
    double getMouseX();

    double getMouseY();

    Node getTarget();

    void setTarget(Node target);
}
