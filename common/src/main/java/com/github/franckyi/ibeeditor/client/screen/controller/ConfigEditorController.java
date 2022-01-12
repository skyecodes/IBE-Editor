package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.ConfigScreenView;

public class ConfigEditorController extends CategoryEntryScreenController<ConfigEditorScreenModel, ConfigScreenView> {
    public ConfigEditorController(ConfigEditorScreenModel model, ConfigScreenView view) {
        super(model, view);
    }
}
