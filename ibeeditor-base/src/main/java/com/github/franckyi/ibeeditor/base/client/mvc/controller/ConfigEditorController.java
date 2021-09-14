package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ConfigEditorView;

public class ConfigEditorController extends ListEditorController<ConfigEditorModel, ConfigEditorView> {
    public ConfigEditorController(ConfigEditorModel model, ConfigEditorView view) {
        super(model, view);
    }
}
