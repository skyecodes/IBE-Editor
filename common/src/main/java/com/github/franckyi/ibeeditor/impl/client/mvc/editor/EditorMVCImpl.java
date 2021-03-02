package com.github.franckyi.ibeeditor.impl.client.mvc.editor;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.EditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.EditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.EditorViewImpl;

public class EditorMVCImpl implements EditorMVC {
    public static final EditorMVC INSTANCE = new EditorMVCImpl();

    private EditorMVCImpl() {
    }

    @Override
    public EditorView createView() {
        return new EditorViewImpl();
    }

    @Override
    public EditorController createController(EditorModel model, EditorView view) {
        return new EditorControllerImpl(model, view);
    }
}
