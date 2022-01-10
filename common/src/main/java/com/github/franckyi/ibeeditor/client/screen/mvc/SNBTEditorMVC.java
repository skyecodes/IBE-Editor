package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.SNBTEditorController;
import com.github.franckyi.ibeeditor.client.screen.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.SNBTEditorView;

public final class SNBTEditorMVC extends SimpleMVC<SNBTEditorModel, SNBTEditorView, SNBTEditorController> {
    public static final SNBTEditorMVC INSTANCE = new SNBTEditorMVC();

    private SNBTEditorMVC() {
        super(SNBTEditorView::new, SNBTEditorController::new);
    }
}
