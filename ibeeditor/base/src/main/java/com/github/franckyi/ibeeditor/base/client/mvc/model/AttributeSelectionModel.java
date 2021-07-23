package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;

import java.util.function.Consumer;

public class AttributeSelectionModel implements Model {
    private final String attributeName;
    private final Consumer<String> action;

    public AttributeSelectionModel(String attributeName, Consumer<String> action) {
        this.attributeName = attributeName;
        this.action = action;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Consumer<String> getAction() {
        return action;
    }
}
