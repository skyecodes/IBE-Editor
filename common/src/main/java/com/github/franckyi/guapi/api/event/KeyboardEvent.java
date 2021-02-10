package com.github.franckyi.guapi.api.event;

public interface KeyboardEvent extends ScreenEvent {
    int getModifiers();

    default boolean isShiftKeyDown() {
        return (getModifiers() & 0x1) != 0;
    }

    default boolean isControlKeyDown() {
        return (getModifiers() & 0x2) != 0;
    }

    default boolean isAltKeyDown() {
        return (getModifiers() & 0x4) != 0;
    }

    default boolean isSuperKeyDown() {
        return (getModifiers() & 0x8) != 0;
    }

    default boolean isCapsLockEnabled() {
        return (getModifiers() & 0x10) != 0;
    }

    default boolean isNumLockEnabled() {
        return (getModifiers() & 0x20) != 0;
    }
}
