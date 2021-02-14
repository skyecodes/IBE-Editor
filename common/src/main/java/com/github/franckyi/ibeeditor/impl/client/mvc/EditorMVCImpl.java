package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.EditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.EditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.EditorViewImpl;

public class EditorMVCImpl implements EditorMVC {
    public static final EditorMVC INSTANCE = new EditorMVCImpl();

    private EditorMVCImpl() {
    }

    @Override
    public EditorView createView() {
        return new EditorViewImpl();
    }

    @Override
    public EditorController getController() {
        return EditorControllerImpl.INSTANCE;
    }
}
