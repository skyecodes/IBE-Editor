package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.node.TextField;

import java.util.function.Consumer;

public class TextProperty extends EmptyProperty<String> {

    private TextField textField;

    public TextProperty(String name, String value, Consumer<String> action) {
        super(name, value, action);
        textField = new TextField(value);
        this.getNode().getChildren().add(textField);
    }

    @Override
    public String getValue() {
        return textField.getText();
    }

}
