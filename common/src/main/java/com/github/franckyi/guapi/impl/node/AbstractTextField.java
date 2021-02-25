package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.*;
import com.github.franckyi.gamehooks.api.common.Text;
import com.github.franckyi.guapi.api.node.TextField;

import java.util.function.Predicate;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public abstract class AbstractTextField extends AbstractLabeled implements TextField {
    private final StringProperty textProperty = PropertyFactory.ofString("");
    private final IntegerProperty maxLengthProperty = PropertyFactory.ofInteger(Integer.MAX_VALUE);
    private final ObjectProperty<Predicate<String>> validatorProperty = PropertyFactory.ofObject(s -> true);
    private final BooleanProperty validationForcedProperty = PropertyFactory.ofBoolean();
    protected final BooleanProperty validProperty = PropertyFactory.ofBoolean();
    private final ObservableBooleanValue validPropertyReadOnly = PropertyFactory.readOnly(validProperty);

    protected AbstractTextField() {
        this("");
    }

    protected AbstractTextField(String value) {
        this(emptyText(), value);
    }

    protected AbstractTextField(String label, String value) {
        this(text(label), value);
    }

    protected AbstractTextField(Text label, String value) {
        super(label);
        setText(value);
        textProperty().addListener(this::updateValid);
        validatorProperty().addListener(this::updateValid);
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

    @Override
    public BooleanProperty validationForcedProperty() {
        return validationForcedProperty;
    }

    @Override
    public ObservableBooleanValue validProperty() {
        return validPropertyReadOnly;
    }

    private void updateValid() {
        validProperty.setValue(getValidator().test(getText()));
    }
}
