package com.github.franckyi.ibeeditor.client.screen.controller.selection;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ListSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.ListSelectionScreenView;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class ListSelectionScreenController extends AbstractController<ListSelectionScreenModel, ListSelectionScreenView> {
    public ListSelectionScreenController(ListSelectionScreenModel model, ListSelectionScreenView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getHeaderLabel().setLabel(ModTexts.title(ModTexts.choose(model.getTitle())));
        view.getSearchField().textProperty().addListener(this::filter);
        model.getElements().forEach(item -> {
            if (item.getId().toString().equals(model.getInitialValue())) {
                view.getListView().setFocusedElement(item);
            }
        });
        view.getListView().focusedElementProperty().addListener(this::refreshButton);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        view.getDoneButton().onAction(() -> {
            model.getAction().accept(view.getListView().getFocusedElement().getId().toString());
            Guapi.getScreenHandler().hideScene();
        });
        refreshButton();
        filter("");
    }

    private void filter(String filter) {
        view.getListView().getItems().setAll(model.getElements()
                .stream()
                .filter(item -> item.matches(filter))
                .limit(ClientConfiguration.INSTANCE.getSelectionScreenMaxItems())
                .toList());
    }

    private void refreshButton() {
        view.getDoneButton().setDisable(view.getListView().getFocusedElement() == null);
    }
}
