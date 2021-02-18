package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.guapi.api.node.TextField;

import java.util.function.Predicate;

public abstract class AbstractTextField extends AbstractLabeled implements TextField {
    private final StringProperty textProperty = PropertyFactory.ofString("");
    private final IntegerProperty maxLengthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);
    private final ObjectProperty<Predicate<String>> validatorProperty = PropertyFactory.ofObject(s -> true);

    protected AbstractTextField() {
        this("");
    }

    protected AbstractTextField(String value) {
        this(Text.EMPTY, value);
    }

    protected AbstractTextField(String label, String value) {
        this(Text.literal(label), value);
    }

    protected AbstractTextField(Text label, String value) {
        super(label);
        setText(value);
    }

    @Override
    public StringProperty textProperty() {
        return textProperty;
    }

    @Override
    public IntegerProperty maxLengthProperty() {
        return maxLengthProperty;
    }

    @Override
    public ObjectProperty<Predicate<String>> validatorProperty() {
        return validatorProperty;
    }
}
