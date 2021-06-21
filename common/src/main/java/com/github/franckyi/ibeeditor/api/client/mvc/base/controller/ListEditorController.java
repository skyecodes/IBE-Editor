package com.github.franckyi.ibeeditor.api.client.mvc.base.controller;

import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;

public interface ListEditorController<M extends ListEditorModel, V extends ListEditorView> extends Controller<M, V> {
}
