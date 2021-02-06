package com.github.franckyi.guapi.node;

public class Button extends Labeled {
    public Button() {
        this("");
    }

    public Button(String text) {
        super(text);
        textProperty().addListener(event -> computeWidth());
        computeSize();
    }

    @Override
    public String toString() {
        return "Button{" +
                "text=" + getText() +
                '}';
    }
}
