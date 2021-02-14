package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.util.ScreenEventType;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;

public class EditorControllerImpl implements EditorController {
    public static final EditorController INSTANCE = new EditorControllerImpl();

    private EditorControllerImpl() {
    }

    @Override
    public void init(EditorModel model, EditorView view) {
        view.getDoneButton().addListener(ScreenEventType.MOUSE_CLICKED, event -> GUAPI.getScreenHandler().hide());
    }
}
