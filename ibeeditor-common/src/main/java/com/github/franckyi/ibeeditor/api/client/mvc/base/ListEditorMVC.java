package com.github.franckyi.ibeeditor.api.client.mvc.base;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.ListEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;

public interface ListEditorMVC<M extends ListEditorModel, V extends ListEditorView, C extends ListEditorController<M, V>> extends SimpleMVC<M, V, C> {
}
