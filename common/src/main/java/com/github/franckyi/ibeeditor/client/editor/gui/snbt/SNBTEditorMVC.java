package com.github.franckyi.ibeeditor.client.editor.gui.snbt;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class SNBTEditorMVC extends SimpleMVC<SNBTEditorModel, SNBTEditorView, SNBTEditorController> {
    public static final SNBTEditorMVC INSTANCE = new SNBTEditorMVC();

    private SNBTEditorMVC() {
        super(SNBTEditorView::new, SNBTEditorController::new);
    }
}
