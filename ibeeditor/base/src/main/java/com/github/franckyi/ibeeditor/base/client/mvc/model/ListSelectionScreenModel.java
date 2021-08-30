package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.mvc.Model;

import java.util.List;
import java.util.function.Consumer;

public class ListSelectionScreenModel implements Model {
    private final String initialValue;
    private final List<? extends ListSelectionItemModel> items;
    private final Consumer<String> action;
    private final IText title;

    public ListSelectionScreenModel(IText title, String initialValue, List<? extends ListSelectionItemModel> items, Consumer<String> action) {
        this.title = title;
        this.initialValue = initialValue;
        this.items = items;
        this.action = action;
    }

    public IText getTitle() {
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
