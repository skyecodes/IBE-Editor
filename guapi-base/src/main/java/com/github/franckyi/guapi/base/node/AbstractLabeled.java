package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.Labeled;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<IText> labelProperty = ObjectProperty.create();

    protected AbstractLabeled(IText label) {
        setLabel(label);
    }

    @Override
    public ObjectProperty<IText> labelProperty() {
        return labelProperty;
    }
}
