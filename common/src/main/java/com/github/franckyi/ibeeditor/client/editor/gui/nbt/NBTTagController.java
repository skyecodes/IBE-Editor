package com.github.franckyi.ibeeditor.client.editor.gui.nbt;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.guapi.api.mvc.Controller;

public class NBTTagController extends AbstractController<NBTTagModel, NBTTagView> implements Controller<NBTTagModel, NBTTagView> {
    public NBTTagController(NBTTagModel model, NBTTagView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getNameField().setText(model.getName());
        view.getValueField().setText(model.getValue());
        softBind(model.nameProperty(), view.getNameField().textProperty());
        softBind(model.valueProperty(), view.getValueField().textProperty());
        model.validProperty().unbind();
        model.validProperty().bind(view.getValueField().validProperty());
        if (model.getName() == null) {
            view.getRoot().getChildren().removeAll(view.getNameField(), view.getSeparator());
        }
        if (model.getValue() == null) {
            view.getRoot().getChildren().remove(view.getValueField());
        }
    }
}
