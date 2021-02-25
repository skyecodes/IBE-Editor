package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.Labeled;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<Text> labelProperty = PropertyFactory.ofObject();

    protected AbstractLabeled(Text label) {
        setLabel(label);
    }

    @Override
    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }
}
