package com.github.franckyi.guapi.api.node.builder.generic;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.TextField;

import java.util.Collection;
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

    default N validationForced(boolean value) {
        return with(n -> n.setValidationForced(value));
    }

    default N suggestions(String... value) {
        return with(n -> n.getSuggestions().setAll(value));
    }

    default N suggestion(Collection<? extends String> value) {
        return with(n -> n.getSuggestions().setAll(value));
    }

    default N placeholder(Text value) {
        return with(n -> n.setPlaceholder(value));
    }
}
