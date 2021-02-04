package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.event.MouseEvent;
import com.github.franckyi.guapi.event.ScreenEventType;

public abstract class Control extends Node {
    @Override
    public <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            event.setTarget(this);
            notifyEvent(type, event);
        }
    }
}
