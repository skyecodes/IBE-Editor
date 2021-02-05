package com.github.franckyi.guapi.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.factory.PropertyFactory;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.event.ActionEvent;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Color;

public class Button extends Labeled {
    private final IntegerProperty disabledColorProperty = PropertyFactory.ofInteger(Color.rgb(160, 160, 160));

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

    public int getDisabledColor() {
        return disabledColorProperty().getValue();
    }

    public IntegerProperty disabledColorProperty() {
        return disabledColorProperty;
    }

    public void setDisabledColor(int value) {
        disabledColorProperty().setValue(value);
    }

    @Override
    public String toString() {
        return "Label{" +
                "text=" + getText() +
                '}';
    }
}
