package com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.controller.EditorTagController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.EditorTagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.EditorTagView;

public class EditorTagControllerImpl extends AbstractController<EditorTagModel, EditorTagView> implements EditorTagController {
    public EditorTagControllerImpl(EditorTagModel model, EditorTagView view) {
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
