package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.SNBTEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.SNBTEditorView;

public final class SNBTEditorMVC extends SimpleMVC<SNBTEditorModel, SNBTEditorView, SNBTEditorController> {
    public static final SNBTEditorMVC INSTANCE = new SNBTEditorMVC();

    private SNBTEditorMVC() {
        super(SNBTEditorView::new, SNBTEditorController::new);
    }
}
