package com.github.franckyi.guapi.impl.event;

import com.github.franckyi.guapi.api.event.TypeEvent;

public class TypeEventImpl extends KeyboardEventImpl implements TypeEvent {
    protected final char character;

    public TypeEventImpl(char character, int modifiers) {
        super(modifiers);
        this.character = character;
    }

    @Override
    public char getCharacter() {
        return character;
    }
}
