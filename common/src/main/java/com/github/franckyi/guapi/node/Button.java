package com.github.franckyi.guapi.node;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.event.ActionEvent;
import com.github.franckyi.guapi.util.Align;

public class Button extends Labeled {
    public Button() {
        this("");
    }

    @Override
    public void action(ActionEvent event) {
        GameHooks.client().sound().playButtonSound();
    }

    public Button(String text) {
        super(text);
        setShadow(true);
        setTextAlign(Align.CENTER);
        computeSize();
    }

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getText() +
                '}';
    }
}
