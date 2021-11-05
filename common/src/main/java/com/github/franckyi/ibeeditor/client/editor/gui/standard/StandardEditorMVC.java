package com.github.franckyi.ibeeditor.client.editor.gui.standard;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class StandardEditorMVC extends SimpleMVC<StandardEditorModel<?, ?>, StandardEditorView, StandardEditorController> {
    public static final StandardEditorMVC INSTANCE = new StandardEditorMVC();

    private StandardEditorMVC() {
        super(StandardEditorView::new, StandardEditorController::new);
    }
}
