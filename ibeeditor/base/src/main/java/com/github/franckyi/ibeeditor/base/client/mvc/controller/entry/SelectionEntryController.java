package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.SelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.SelectionEntryView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class SelectionEntryController<M extends SelectionEntryModel, V extends SelectionEntryView> extends StringEntryController<M, V> {
    public SelectionEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setPlaceholder(translated(model.getSuggestionScreenTitle()));
        view.getTextField().getSuggestions().setAll(model.getSuggestions());
        view.getTooltip().with(translated(model.getSuggestionScreenTitle()));
        view.getItemListButton().onAction(this::openItemList);
        if (model.getLabel() == null) {
            view.getRoot().getChildren().remove(view.getLabel());
        }
    }

    private void openItemList() {
        ModScreenHandler.openListSelectionScreen(model.getSuggestionScreenTitle(),
                model.getValue().contains(":") ? model.getValue() : "minecraft:" + model.getValue(),
                model.getSelectionItems(), model::setValue);
    }
}
