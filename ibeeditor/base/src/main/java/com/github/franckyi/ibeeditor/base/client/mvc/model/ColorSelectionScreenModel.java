package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.function.Consumer;

public class ColorSelectionScreenModel implements Model {
    private final Consumer<String> action;
    private final StringProperty valueProperty = DataBindings.getPropertyFactory().createStringProperty();

    public ColorSelectionScreenModel(Consumer<String> action) {
        this.action = action;
    }

    public void apply() {
        action.accept(getValue());
    }

    public String getValue() {
        return valueProperty().getValue();
    }

    public StringProperty valueProperty() {
        return valueProperty;
    }
}
