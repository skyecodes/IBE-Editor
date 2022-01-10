package com.github.franckyi.ibeeditor.client.screen.model.selection;

import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public record ListSelectionScreenModel(MutableComponent title, String initialValue,
                                       List<? extends ListSelectionElementModel> items,
                                       Consumer<String> action) implements Model {

    public MutableComponent getTitle() {
        return title;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public List<? extends ListSelectionElementModel> getElements() {
        return items;
    }

    public Consumer<String> getAction() {
        return action;
    }
}
