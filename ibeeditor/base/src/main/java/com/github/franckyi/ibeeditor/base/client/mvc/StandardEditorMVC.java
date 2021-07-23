package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.StandardEditorView;

public class StandardEditorMVC implements SimpleMVC<StandardEditorModel<?, ?>, StandardEditorView, StandardEditorController> {
    public static final StandardEditorMVC INSTANCE = new StandardEditorMVC();

    private StandardEditorMVC() {
    }

    @Override
    public StandardEditorView createView() {
        return new StandardEditorView();
    }

    @Override
    public StandardEditorController createController(StandardEditorModel<?, ?> model, StandardEditorView view) {
        return new StandardEditorController(model, view);
    }
}
