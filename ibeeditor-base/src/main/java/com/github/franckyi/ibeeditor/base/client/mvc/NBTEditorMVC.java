package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTEditorView;

public final class NBTEditorMVC extends SimpleMVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVC();

    private NBTEditorMVC() {
        super(NBTEditorView::new, NBTEditorController::new);
    }
}
