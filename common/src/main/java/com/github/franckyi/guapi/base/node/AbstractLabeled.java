package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Labeled;
import net.minecraft.network.chat.Component;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<Component> labelProperty = ObjectProperty.create();

    protected AbstractLabeled(Component label) {
        setLabel(label);
    }

    @Override
    public ObjectProperty<Component> labelProperty() {
        return labelProperty;
    }
}
