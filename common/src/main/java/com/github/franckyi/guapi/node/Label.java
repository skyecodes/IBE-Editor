package com.github.franckyi.guapi.node;

public class Label extends Labeled {
    public Label() {
        this("");
    }

    public Label(String text) {
        super(text);
        computeSize();
    }

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getText() +
                '}';
    }
}
