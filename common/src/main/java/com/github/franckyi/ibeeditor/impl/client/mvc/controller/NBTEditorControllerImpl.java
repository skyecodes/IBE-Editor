package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;

public class NBTEditorControllerImpl implements NBTEditorController {
    public static final NBTEditorController INSTANCE = new NBTEditorControllerImpl();

    protected NBTEditorControllerImpl() {
    }

    @Override
    public void init(NBTEditorModel model, NBTEditorView view) {
        view.getTagTree().rootItemProperty().bind(model.tagProperty());
        view.getDoneButton().onAction(event -> GUAPI.getScreenHandler().hide());
        view.getCancelButton().onAction(event -> GUAPI.setDebugMode(!GUAPI.isDebugMode()));
    }
}
