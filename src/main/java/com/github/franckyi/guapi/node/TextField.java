package com.github.franckyi.guapi.node;

public class TextField extends TextFieldBase<String> {

    public TextField() {
        this("");
    }

    public TextField(String text) {
        this.computeSize();
        this.updateSize();
        this.setValue(text);
    }

    @Override
    public String getValue() {
        return this.getText();
    }

    @Override
    public void setValue(String text) {
        this.setText(text);
    }

}
