package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.guapi.api.node.TextField;

import java.util.function.Predicate;

public interface GenericTextFieldBuilder<N extends TextField> extends TextField, GenericLabeledBuilder<N> {
    default N text(String value) {
        return with(n -> n.setText(value));
    }

    default N maxLength(int value) {
        return with(n -> n.setMaxLength(value));
    }

    default N validator(Predicate<String> value) {
        return with(n -> n.setValidator(value));
    }
}
