package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.ConfigEditorView;

public class ConfigEditorController extends ListEditorController<ConfigEditorModel, ConfigEditorView> {
    public ConfigEditorController(ConfigEditorModel model, ConfigEditorView view) {
        super(model, view);
    }
}
