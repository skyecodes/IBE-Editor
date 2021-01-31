package com.github.franckyi.guapi.common.event;

public abstract class ScreenEvent {
    private boolean consumed = false;

    public ScreenEvent() {
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        this.consumed = true;
    }
}
