package com.github.franckyi.guapi.common.event;

public abstract class ScreenEvent {
    protected boolean consumed = false;

    protected ScreenEvent() {
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        this.consumed = true;
    }

    public interface Listener<E extends ScreenEvent> {
        void handle(E event);
    }
}
