package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.StandardEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.controller.StandardEditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.view.StandardEditorViewImpl;

public class StandardEditorMVCImpl implements StandardEditorMVC {
    public static final StandardEditorMVC INSTANCE = new StandardEditorMVCImpl();

    private StandardEditorMVCImpl() {
    }

    @Override
    public StandardEditorView createView() {
        return new StandardEditorViewImpl();
    }

    @Override
    public StandardEditorController createController(StandardEditorModel model, StandardEditorView view) {
        return new StandardEditorControllerImpl(model, view);
    }
}
