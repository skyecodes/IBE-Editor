package com.github.franckyi.ibeeditor.impl.client.mvc.config;

import com.github.franckyi.ibeeditor.api.client.mvc.config.ConfigEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.config.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.view.ConfigEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.controller.ConfigEditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.view.ConfigEditorViewImpl;

public class ConfigEditorMVCImpl implements ConfigEditorMVC {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVCImpl();

    protected ConfigEditorMVCImpl() {
    }

    @Override
    public ConfigEditorView createView() {
        return new ConfigEditorViewImpl();
    }

    @Override
    public ConfigEditorController createController(ConfigEditorModel model, ConfigEditorView view) {
        return new ConfigEditorControllerImpl(model, view);
    }
}
