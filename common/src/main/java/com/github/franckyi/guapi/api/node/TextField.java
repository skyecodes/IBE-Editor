package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.StringProperty;

import java.util.function.Predicate;

public interface TextField extends Labeled {
    default String getText() {
        return textProperty().getValue();
    }

    StringProperty textProperty();

    default void setText(String value) {
        textProperty().setValue(value);
    }

    default int getMaxLength() {
        return maxLengthProperty().getValue();
    }

    IntegerProperty maxLengthProperty();

    default void setMaxLength(int value) {
        maxLengthProperty().setValue(value);
    }

    default Predicate<String> getValidator() {
        return validatorProperty().getValue();
    }

    ObjectProperty<Predicate<String>> validatorProperty();

    default void setValidator(Predicate<String> value) {
        validatorProperty().setValue(value);
    }
}
