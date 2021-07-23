package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTEditorView;

public class NBTEditorMVC implements SimpleMVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVC();

    protected NBTEditorMVC() {
    }

    @Override
    public NBTEditorView createView() {
        return new NBTEditorView();
    }

    @Override
    public NBTEditorController createController(NBTEditorModel model, NBTEditorView view) {
        return new NBTEditorController(model, view);
    }
}
