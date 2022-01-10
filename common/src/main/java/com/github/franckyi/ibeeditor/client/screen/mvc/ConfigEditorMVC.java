package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.ConfigEditorView;

public final class ConfigEditorMVC extends SimpleMVC<ConfigEditorModel, ConfigEditorView, ConfigEditorController> {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVC();

    private ConfigEditorMVC() {
        super(ConfigEditorView::new, ConfigEditorController::new);
    }
}
