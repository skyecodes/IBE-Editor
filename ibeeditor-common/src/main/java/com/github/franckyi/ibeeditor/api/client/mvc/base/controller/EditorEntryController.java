package com.github.franckyi.ibeeditor.api.client.mvc.base.controller;

import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

public interface EditorEntryController<M extends EditorEntryModel, V extends EditorEntryView> extends Controller<M, V> {
}
