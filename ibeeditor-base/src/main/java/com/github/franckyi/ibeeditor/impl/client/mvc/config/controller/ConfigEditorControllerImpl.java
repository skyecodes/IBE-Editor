package com.github.franckyi.ibeeditor.impl.client.mvc.config.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.config.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.view.ConfigEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.AbstractListEditorController;

public class ConfigEditorControllerImpl extends AbstractListEditorController<ConfigEditorModel, ConfigEditorView> implements ConfigEditorController {
    public ConfigEditorControllerImpl(ConfigEditorModel model, ConfigEditorView view) {
        super(model, view);
    }
}
