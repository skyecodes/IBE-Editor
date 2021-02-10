package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.KeyboardEvent;

public abstract class KeyboardEventImpl extends ScreenEventImpl implements KeyboardEvent {
    protected final int modifiers;

    protected KeyboardEventImpl(int modifiers) {
        this.modifiers = modifiers;
    }

    @Override
    public int getModifiers() {
        return modifiers;
    }

}
