package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.text.Text;

public abstract class Labeled extends Control {
    private final ObjectProperty<Text> labelProperty = PropertyFactory.ofObject(Text.EMPTY);
    private final ObjectProperty<?> labelComponentProperty = PropertyFactory.ofObject();

    protected boolean shouldUpdateLabelComponent = true;

    protected Labeled(Text label) {
        labelProperty().addListener(this::shouldUpdateLabelComponent);
        setLabel(label);
        this.updateLabelComponent();
    }

    public Text getLabel() {
        return labelProperty().getValue();
    }

    public ObjectProperty<Text> labelProperty() {
        return labelProperty;
    }

    public void setLabel(Text value) {
        labelProperty().setValue(value);
    }

    public <T> T getLabelComponent() {
        return this.<T>labelComponentProperty().getValue();
    }

    @SuppressWarnings("unchecked")
    public <T> ObjectProperty<T> labelComponentProperty() {
        return (ObjectProperty<T>) labelComponentProperty;
    }

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
