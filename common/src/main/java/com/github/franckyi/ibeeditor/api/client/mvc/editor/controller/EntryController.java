package com.github.franckyi.ibeeditor.api.client.mvc.editor.controller;

import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;

public interface EntryController<M extends EntryModel, V extends EntryView> extends Controller<M, V> {
}
