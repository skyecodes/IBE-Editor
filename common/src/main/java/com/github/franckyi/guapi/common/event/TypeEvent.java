package com.github.franckyi.guapi.common.event;

public class TypeEvent {
    private final char character;
    private final int keyCode;

    public TypeEvent(char character, int keyCode) {
        this.character = character;
        this.keyCode = keyCode;
    }

    public char getCharacter() {
        return character;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
