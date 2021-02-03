package com.github.franckyi.guapi.event;

public class KeyEvent extends KeyboardEvent {
    protected final int keyCode, scanCode;

    public KeyEvent(int keyCode, int scanCode, int modifiers) {
        super(modifiers);
        this.keyCode = keyCode;
        this.scanCode = scanCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    @Override
    public String toString() {
        return "KeyEvent{" +
                "keyCode=" + keyCode +
                ", scanCode=" + scanCode +
                ", modifiers=" + modifiers +
                ", consumed=" + consumed +
                '}';
    }
}
