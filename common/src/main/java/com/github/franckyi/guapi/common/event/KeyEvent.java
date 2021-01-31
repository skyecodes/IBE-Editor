package com.github.franckyi.guapi.common.event;

public class KeyEvent extends ScreenEvent {
    private final int keyCode, scanCode, modifiers;

    public KeyEvent(int keyCode, int scanCode, int modifiers) {
        this.keyCode = keyCode;
        this.scanCode = scanCode;
        this.modifiers = modifiers;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getModifiers() {
        return modifiers;
    }
}
