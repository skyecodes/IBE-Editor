package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.StandardEditorView;

public final class StandardEditorMVC extends SimpleMVC<StandardEditorModel, StandardEditorView, StandardEditorController> {
    public static final StandardEditorMVC INSTANCE = new StandardEditorMVC();

    private StandardEditorMVC() {
        super(StandardEditorView::new, StandardEditorController::new);
    }
}
