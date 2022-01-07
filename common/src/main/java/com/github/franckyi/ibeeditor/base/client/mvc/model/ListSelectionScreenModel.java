package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public final class ListSelectionScreenModel implements Model {
    private final MutableComponent title;
    private final String initialValue;
    private final List<? extends ListSelectionItemModel> items;
    private final Consumer<String> action;

    public ListSelectionScreenModel(MutableComponent title, String initialValue, List<? extends ListSelectionItemModel> items, Consumer<String> action) {
        this.title = title;
        this.initialValue = initialValue;
        this.items = items;
        this.action = action;
    }

    public MutableComponent getTitle() {
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
