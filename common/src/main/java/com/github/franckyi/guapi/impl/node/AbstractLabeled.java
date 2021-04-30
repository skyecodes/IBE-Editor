package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.Labeled;
import com.github.franckyi.minecraft.api.common.text.Text;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<Text> labelProperty = DataBindings.getPropertyFactory().createObjectProperty();

    protected AbstractLabeled(Text label) {
        setLabel(label);
    }

    @Override
    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }
}
