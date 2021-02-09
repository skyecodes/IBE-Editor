package com.github.franckyi.guapi.node;

import com.github.franckyi.gamehooks.api.common.text.Text;

public class Button extends Labeled {
    public Button() {
        this(Text.EMPTY);
    }

    public Button(String text) {
        this(Text.literal(text));
    }

    public Button(Text text) {
        super(text);
        labelProperty().addListener(this::shouldComputeSize);
    }

    @Override
    public String toString() {
        return "Button{" +
                "text=" + getLabel() +
                '}';
    }
}
