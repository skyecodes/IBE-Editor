package com.github.franckyi.guapi.common.node;

import com.github.franckyi.guapi.common.event.MouseEvent;
import com.github.franckyi.guapi.common.event.ScreenEventType;

public abstract class Control extends Node {
    @Override
    public <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            event.setTarget(this);
            type.onEvent(this, event);
            eventHandlerDelegate.handleEvent(type, event);
        }
    }
}
