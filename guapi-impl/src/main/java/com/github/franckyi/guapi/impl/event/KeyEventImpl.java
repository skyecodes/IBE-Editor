package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.KeyEvent;

public class KeyEventImpl extends KeyboardEventImpl implements KeyEvent {
    protected final int keyCode, scanCode;

    public KeyEventImpl(int keyCode, int scanCode, int modifiers) {
        super(modifiers);
        this.keyCode = keyCode;
        this.scanCode = scanCode;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public int getScanCode() {
        return scanCode;
    }
}
