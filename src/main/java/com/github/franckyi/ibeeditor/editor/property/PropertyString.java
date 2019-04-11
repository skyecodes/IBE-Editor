package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.node.TextField;

import java.util.function.Consumer;

public class PropertyString extends EmptyProperty<String> {

    protected TextField textField;

    public PropertyString(String name, String value, Consumer<String> action) {
        super(name, value, action);
    }

    @Override
    public String getValue() {
        return textField.getText();
    }

    @Override
    protected void setValue(String value) {
        textField.setText(value);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(textField = new TextField());
    }

    @Override
    protected void updateSize(int listWidth) {
        textField.setPrefWidth(listWidth - 85);
    }
}
