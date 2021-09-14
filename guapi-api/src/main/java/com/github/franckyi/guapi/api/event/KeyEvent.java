package com.github.franckyi.guapi.api.event;

public interface KeyEvent extends KeyboardEvent {
    int getKeyCode();

    int getScanCode();
}
