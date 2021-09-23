package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class SNBTEditorModel implements Model {
    private final StringProperty valueProperty;
    private final Consumer<String> action;
    private final Component disabledTooltip;

    public SNBTEditorModel(String value, Consumer<String> action, Component disabledTooltip) {
        valueProperty = StringProperty.create(value);
        this.action = action;
        this.disabledTooltip = disabledTooltip;
    }

    public String getValue() {
        return valueProperty().getValue();
    }

    public StringProperty valueProperty() {
        return valueProperty;
    }

    public void setValue(String value) {
        valueProperty().setValue(value);
    }

    public Consumer<String> getAction() {
        return action;
    }

    public Component getDisabledTooltip() {
        return disabledTooltip;
    }

    public boolean canSave() {
        return getDisabledTooltip() == null;
    }

    public void apply() {
        action.accept(getValue());
    }
}
