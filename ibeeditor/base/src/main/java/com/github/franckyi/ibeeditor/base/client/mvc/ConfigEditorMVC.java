package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ConfigEditorView;

public final class ConfigEditorMVC implements SimpleMVC<ConfigEditorModel, ConfigEditorView, ConfigEditorController> {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVC();

    protected ConfigEditorMVC() {
    }

    @Override
    public ConfigEditorView createView() {
        return new ConfigEditorView();
    }

    @Override
    public ConfigEditorController createController(ConfigEditorModel model, ConfigEditorView view) {
        return new ConfigEditorController(model, view);
    }
}
