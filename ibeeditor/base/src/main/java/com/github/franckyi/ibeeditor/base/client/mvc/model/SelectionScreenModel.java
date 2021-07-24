package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;

import java.util.List;
import java.util.function.Consumer;

public class SelectionScreenModel implements Model {
    private final String initialValue;
    private final List<? extends SelectionItemModel> items;
    private final Consumer<String> action;
    private final String title;

    public SelectionScreenModel(String title, String initialValue, List<? extends SelectionItemModel> items, Consumer<String> action) {
        this.title = title;
        this.initialValue = initialValue;
        this.items = items;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public List<? extends SelectionItemModel> getItems() {
        return items;
    }

    public Consumer<String> getAction() {
        return action;
    }
}
