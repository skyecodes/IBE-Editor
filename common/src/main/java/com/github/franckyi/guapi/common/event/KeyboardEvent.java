package com.github.franckyi.guapi.common.event;

public abstract class KeyboardEvent extends ScreenEvent {
    protected final int modifiers;

    protected KeyboardEvent(int modifiers) {
        this.modifiers = modifiers;
    }

    public int getModifiers() {
        return modifiers;
    }

    public boolean isShiftKeyDown() {
        return (modifiers & 0x1) != 0;
    }

    public boolean isControlKeyDown() {
        return (modifiers & 0x2) != 0;
    }

    public boolean isAltKeyDown() {
        return (modifiers & 0x4) != 0;
    }

    public boolean isSuperKeyDown() {
        return (modifiers & 0x8) != 0;
    }

    public boolean isCapsLockEnabled() {
        return (modifiers & 0x10) != 0;
    }

    public boolean isNumLockEnabled() {
        return (modifiers & 0x20) != 0;
    }
}
