package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.StandardEditorView;

public final class StandardEditorMVC extends SimpleMVC<StandardEditorModel<?, ?>, StandardEditorView, StandardEditorController> {
    public static final StandardEditorMVC INSTANCE = new StandardEditorMVC();

    private StandardEditorMVC() {
        super(StandardEditorView::new, StandardEditorController::new);
    }
}
