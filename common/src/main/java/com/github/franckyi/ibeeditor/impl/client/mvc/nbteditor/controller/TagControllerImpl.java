package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.TagView;

public class TagControllerImpl extends AbstractController<TagModel, TagView> implements TagController {
    public TagControllerImpl(TagModel model, TagView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameField().setText(model.getName());
        view.getValueField().setText(model.getValue());
        model.nameProperty().bindBidirectional(view.getNameField().textProperty());
        model.valueProperty().bindBidirectional(view.getValueField().textProperty());
        model.validProperty().bind(view.getValueField().validProperty());
        if (model.getName() == null) {
            view.getRoot().getChildren().removeAll(view.getNameField(), view.getSeparator());
        }
        if (model.getValue() == null) {
            view.getRoot().getChildren().remove(view.getValueField());
        }
    }
}
