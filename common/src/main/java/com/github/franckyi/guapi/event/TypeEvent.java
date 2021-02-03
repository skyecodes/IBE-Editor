package com.github.franckyi.guapi.event;

public class TypeEvent extends KeyboardEvent {
    protected final char character;

    public TypeEvent(char character, int modifiers) {
        super(modifiers);
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "TypeEvent{" +
                "modifiers=" + modifiers +
                ", consumed=" + consumed +
                ", character=" + character +
                '}';
    }
}
