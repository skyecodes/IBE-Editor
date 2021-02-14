package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;

public interface EditorMVC extends MVC<EditorModel, EditorView, EditorController> {
}
