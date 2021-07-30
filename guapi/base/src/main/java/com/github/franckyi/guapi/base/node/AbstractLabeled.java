package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.Labeled;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<Text> labelProperty = ObjectProperty.create();

    protected AbstractLabeled(Text label) {
        setLabel(label);
    }

    @Override
    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }
}
