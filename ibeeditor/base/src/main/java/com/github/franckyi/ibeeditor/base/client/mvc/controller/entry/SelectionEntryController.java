package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.gameadapter.api.common.text.builder.TranslatedTextBuilder;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.SelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.SelectionEntryView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class SelectionEntryController extends StringEntryController<SelectionEntryModel, SelectionEntryView> {
    public SelectionEntryController(SelectionEntryModel model, SelectionEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().getSuggestions().setAll(model.getSuggestions());
        ((TranslatedTextBuilder) view.getItemListButton().getTooltip().get(0)).with(translated(model.getSuggestionScreenTitle()));
        view.getItemListButton().onAction(this::openItemList);
        if (model.getLabel() == null) {
            view.getRoot().getChildren().remove(view.getLabel());
        }
    }

    private void openItemList() {
        ModScreenHandler.openSelectionScreen(model.getSuggestionScreenTitle(),
                model.getValue().contains(":") ? model.getValue() : "minecraft:" + model.getValue(),
                model.getSelectionItems(), model::setValue);
    }
}
