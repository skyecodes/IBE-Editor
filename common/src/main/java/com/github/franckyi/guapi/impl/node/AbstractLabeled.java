package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.guapi.api.node.Labeled;

public abstract class AbstractLabeled extends AbstractControl implements Labeled {
    private final ObjectProperty<Text> labelProperty = PropertyFactory.ofObject(Text.EMPTY);
    private final ObjectProperty<?> labelComponentProperty = PropertyFactory.ofObject();

    protected boolean shouldUpdateLabelComponent = true;

    protected AbstractLabeled() {
        this(Text.EMPTY);
    }

    protected AbstractLabeled(String label) {
        this(Text.literal(label));
    }

    protected AbstractLabeled(Text label) {
        labelProperty().addListener(this::shouldUpdateLabelComponent);
        setLabel(label);
        this.updateLabelComponent();
    }

    @Override
    public Text getLabel() {
        return labelProperty().getValue();
    }

    @Override
    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }

    @Override
    public void setLabel(Text value) {
        labelProperty().setValue(value);
    }

    @Override
    public <T> T getLabelComponent() {
        return this.<T>labelComponentProperty().getValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ObjectProperty<T> labelComponentProperty() {
        return (ObjectProperty<T>) labelComponentProperty;
    }

    @Override
    public <T> void setLabelComponent(T value) {
        labelComponentProperty().setValue(value);
    }

    @Override
    public boolean checkRender() {
        boolean res = false;
        if (shouldUpdateLabelComponent) {
            updateLabelComponent();
            res = true;
        }
        return res || super.checkRender();
    }

    protected void shouldUpdateLabelComponent() {
        shouldUpdateLabelComponent = true;
    }

    protected void updateLabelComponent() {
        setLabelComponent(GameHooks.common().text().create(getLabel()));
        shouldUpdateLabelComponent = false;
    }
}
