package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.editor.AbstractProperty;

import java.util.function.Consumer;

public class TextProperty extends AbstractProperty<String> {

    private TextField textField;

    public TextProperty(String name, String value, Consumer<String> action) {
        super(name, value, action);
        textField = new TextField(value);
        this.getNode().getChildren().add(textField);
    }

    @Override
    public HBox getNode() {
        return (HBox) super.getNode();
    }

    @Override
    public String getValue() {
        return textField.getText();
    }


}
