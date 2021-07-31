package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SNBTEditorView;

public class SNBTEditorController extends AbstractController<SNBTEditorModel, SNBTEditorView> {
    public SNBTEditorController(SNBTEditorModel model, SNBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getTextArea().setText(model.getValue());
        view.getTextArea().setValidator(s -> CompoundTag.parse(s) != null);
        softBind(view.getTextArea().textProperty(), model.valueProperty());
        if (model.canSave()) {
            view.getDoneButton().disableProperty().bind(view.getTextArea().validProperty().not());
            view.getDoneButton().onAction(event -> model.apply());
        } else {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }
        view.getCancelButton().onAction(event -> Guapi.getScreenHandler().hideScene());
    }
}
