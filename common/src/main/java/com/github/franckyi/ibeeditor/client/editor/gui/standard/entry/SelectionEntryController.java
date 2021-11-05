package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class SelectionEntryController<M extends SelectionEntryModel, V extends SelectionEntryView> extends StringEntryController<M, V> {
    public SelectionEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setPlaceholder(model.getSuggestionScreenTitle());
        view.getTextField().getSuggestions().setAll(model.getSuggestions());
        view.getItemListButton().getTooltip().add(ModTexts.choose(model.getSuggestionScreenTitle()));
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
