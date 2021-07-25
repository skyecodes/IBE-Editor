package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.ClientConfiguration;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SelectionScreenView;

import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class SelectionScreenController extends AbstractController<SelectionScreenModel, SelectionScreenView> {
    public SelectionScreenController(SelectionScreenModel model, SelectionScreenView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getTitleLabel().setLabel(translated("ibeeditor.gui.choose").with(translated(model.getTitle())).gold().bold());
        view.getSearchField().textProperty().addListener(this::filter);
        view.getListView().getItems().forEach(item -> {
            if (item.getId().equals(model.getInitialValue())) {
                view.getListView().setFocusedElement(item);
            }
        });
        view.getListView().focusedElementProperty().addListener(this::refreshButton);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        view.getDoneButton().onAction(() -> {
            model.getAction().accept(view.getListView().getFocusedElement().getId());
            Guapi.getScreenHandler().hideScene();
        });
        refreshButton();
        filter("");
    }

    private void filter(String filter) {
        view.getListView().getItems().setAll(model.getItems()
                .stream()
                .filter(item -> item.matches(filter))
                .limit(ClientConfiguration.INSTANCE.getSelectionScreenMaxItems())
                .collect(Collectors.toList()));
    }

    private void refreshButton() {
        view.getDoneButton().setDisable(view.getListView().getFocusedElement() == null);
    }
}
