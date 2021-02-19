package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

public class TagControllerImpl implements TagController {
    public static final TagController INSTANCE = new TagControllerImpl();

    protected TagControllerImpl() {
    }

    @Override
    public void init(TagModel model, TagView view) {
        view.getNameField().setText(model.getName());
        view.getValueField().setText(model.getValue());
        view.getNameField().textProperty().bindBidirectional(model.nameProperty());
        view.getValueField().textProperty().bindBidirectional(model.valueProperty());
        view.getSeparator().setVisible(model.shouldRenderValue() && model.shouldRenderName());
        view.getNameField().setVisible(model.shouldRenderName());
        view.getValueField().setVisible(model.shouldRenderValue());
    }
}
