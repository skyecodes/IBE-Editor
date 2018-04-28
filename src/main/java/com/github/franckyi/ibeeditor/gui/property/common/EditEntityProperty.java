package com.github.franckyi.ibeeditor.gui.property.common;

import com.github.franckyi.ibeeditor.gui.property.base.BaseProperty;

import java.util.function.Supplier;

public class EditEntityProperty extends BaseProperty<Void> {

    public EditEntityProperty(String name, Supplier<Void> value) {
        super(name, value);
    }
}
