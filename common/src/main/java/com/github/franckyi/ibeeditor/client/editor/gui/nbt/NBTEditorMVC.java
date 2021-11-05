package com.github.franckyi.ibeeditor.client.editor.gui.nbt;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class NBTEditorMVC extends SimpleMVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVC();

    private NBTEditorMVC() {
        super(NBTEditorView::new, NBTEditorController::new);
    }
}
