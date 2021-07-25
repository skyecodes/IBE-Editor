package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;

import java.util.List;
import java.util.function.Consumer;

public class ListSelectionScreenModel implements Model {
    private final String initialValue;
    private final List<? extends ListSelectionItemModel> items;
    private final Consumer<String> action;
    private final String title;

    public ListSelectionScreenModel(String title, String initialValue, List<? extends ListSelectionItemModel> items, Consumer<String> action) {
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

    public List<? extends ListSelectionItemModel> getItems() {
        return items;
    }

    public Consumer<String> getAction() {
        return action;
    }
}
