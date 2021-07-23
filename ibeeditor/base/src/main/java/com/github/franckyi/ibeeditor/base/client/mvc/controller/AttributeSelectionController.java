package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.AttributeSelectionModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.AttributeSelectionView;

import java.util.stream.Collectors;

public class AttributeSelectionController extends AbstractController<AttributeSelectionModel, AttributeSelectionView> {
    public AttributeSelectionController(AttributeSelectionModel model, AttributeSelectionView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getListView().getItems().setAll(Game.getCommon().getRegistries().getAttributes()
                .stream()
                .map(AttributeItemModel::new)
                .collect(Collectors.toList()));
        view.getListView().getItems().forEach(item -> {
            if (item.getAttribute().getId().equals(model.getAttributeName())) {
                view.getListView().setFocusedElement(item);
            }
        });
        view.getListView().focusedElementProperty().addListener(this::refreshButton);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
        view.getDoneButton().onAction(() -> {
            model.getAction().accept(view.getListView().getFocusedElement().getAttribute().getId());
            Guapi.getScreenHandler().hideScene();
        });
        refreshButton();
    }

    private void refreshButton() {
        view.getDoneButton().setDisable(view.getListView().getFocusedElement() == null);
    }
}
