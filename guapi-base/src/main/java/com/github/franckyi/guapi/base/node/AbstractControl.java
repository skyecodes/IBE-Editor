package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.event.MouseEvent;
import com.github.franckyi.guapi.api.node.Control;
import com.github.franckyi.guapi.api.util.ScreenEventType;

public abstract class AbstractControl extends AbstractNode implements Control {
    protected AbstractControl() {
    }

    @Override
    public <E extends MouseEvent> void handleMouseEvent(ScreenEventType<E> type, E event) {
        if (inBounds(event.getMouseX(), event.getMouseY())) {
            event.setTarget(this);
            notifyEvent(type, event);
        }
    }
}
