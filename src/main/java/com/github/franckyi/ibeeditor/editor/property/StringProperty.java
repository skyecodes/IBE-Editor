package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.node.TextField;

import java.util.function.Consumer;

public class StringProperty extends EmptyProperty<String> {

    protected final TextField textField;

    public StringProperty(String name, String value, Consumer<String> action) {
        super(name, value, action);
        textField = new TextField(value);
        this.getNode().getChildren().add(textField);
    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public String getValue() {
        return textField.getText();
    }

    @Override
    public void updateChildrenPos() {
        super.updateChildrenPos();
        if (textField != null && this.getList() != null) {
            this.updateTextFieldSize();
        }
    }

    protected void updateTextFieldSize() {
        textField.setPrefWidth(this.getList().getWidth() - 60);
    }
}
