package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.client.screen.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.NBTEditorView;

public final class NBTEditorMVC extends SimpleMVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVC();

    private NBTEditorMVC() {
        super(NBTEditorView::new, NBTEditorController::new);
    }
}
