package com.github.franckyi.ibeeditor.api.client.mvc.editor;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;

public interface EditorMVC extends SimpleMVC<EditorModel, EditorView, EditorController> {
}
