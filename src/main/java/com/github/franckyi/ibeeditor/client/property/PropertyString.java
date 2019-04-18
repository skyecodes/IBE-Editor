package com.github.franckyi.ibeeditor.client.property;

import com.github.franckyi.guapi.node.TextField;

import java.util.function.Consumer;

public class PropertyString extends LabeledCategory<String> {

    protected TextField textField;

    public PropertyString(String name, String value, Consumer<String> action) {
        super(name, value, action);
    }

    @Override
    public String getValue() {
        return textField.getValue();
    }

    @Override
    protected void setValue(String value) {
        textField.setValue(value);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(textField = new TextField());
        textField.getOnValueChangedListeners().add((oldVal, newVal) -> onValueChanged(oldVal, newVal));
    }

    @Override
    protected void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - 116);
    }
}
