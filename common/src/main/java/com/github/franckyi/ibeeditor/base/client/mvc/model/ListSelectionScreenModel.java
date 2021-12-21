package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.mvc.Model;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public record ListSelectionScreenModel(MutableComponent title, String initialValue,
                                       List<? extends ListSelectionItemModel> items,
                                       Consumer<String> action) implements Model {

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
