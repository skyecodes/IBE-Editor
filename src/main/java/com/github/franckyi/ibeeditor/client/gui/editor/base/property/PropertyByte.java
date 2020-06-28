package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.NumberField;
import com.github.franckyi.guapi.node.ByteField;

import java.util.function.Consumer;

public class PropertyByte extends PropertyNumber<Byte> {

    public PropertyByte(String name, byte initialValue, Consumer<Byte> action) {
        super(name, initialValue, action);
    }

    public PropertyByte(String name, byte initialValue, Consumer<Byte> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyByte(String name, byte initialValue, Consumer<Byte> action, byte min, byte max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Byte> createField(Byte value) {
        return new ByteField(value);
    }
}
