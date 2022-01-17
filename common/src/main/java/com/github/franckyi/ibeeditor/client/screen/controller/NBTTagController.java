package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.client.screen.model.NBTTagModel;
import com.github.franckyi.ibeeditor.client.screen.view.NBTTagView;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class NBTTagController extends AbstractController<NBTTagModel, NBTTagView> implements Controller<NBTTagModel, NBTTagView> {
    public NBTTagController(NBTTagModel model, NBTTagView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameField().textProperty().bindBidirectional(model.nameProperty());
        view.getValueField().textProperty().bindBidirectional(model.valueProperty());
        if (model.getName() == null) {
            view.getRoot().getChildren().removeAll(view.getNameField(), view.getSeparator());
            if (model.getParent() == null) {
                view.getRoot().getChildren().add(label(text("(root)")));
            } else {
                view.getRoot().getChildren().add(1, label(text("(%d)".formatted(model.getParent().getChildren().indexOf(model)))));
            }
        }
        if (model.getValue() == null) {
            view.getRoot().getChildren().remove(view.getValueField());
        } else {
            model.validProperty().bind(view.getValueField().validProperty());
        }
    }
}
