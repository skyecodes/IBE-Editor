package com.github.franckyi.guapi.base.event;

import com.github.franckyi.guapi.api.event.ScreenEvent;

public abstract class ScreenEventImpl implements ScreenEvent {
    protected boolean consumed = false;

    protected ScreenEventImpl() {
    }

    @Override
    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public void consume() {
        this.consumed = true;
    }
}
