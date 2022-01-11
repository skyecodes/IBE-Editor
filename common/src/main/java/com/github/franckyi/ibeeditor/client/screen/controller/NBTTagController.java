package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.client.screen.model.NBTTagModel;
import com.github.franckyi.ibeeditor.client.screen.view.NBTTagView;

public class NBTTagController extends AbstractController<NBTTagModel, NBTTagView> implements Controller<NBTTagModel, NBTTagView> {
    public NBTTagController(NBTTagModel model, NBTTagView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameField().textProperty().bindBidirectional(model.nameProperty());
        view.getValueField().textProperty().bindBidirectional(model.valueProperty());
        model.validProperty().bind(view.getValueField().validProperty());
        if (model.getName() == null) {
            view.getRoot().getChildren().removeAll(view.getNameField(), view.getSeparator());
        }
        if (model.getValue() == null) {
            view.getRoot().getChildren().remove(view.getValueField());
        }
    }
}
