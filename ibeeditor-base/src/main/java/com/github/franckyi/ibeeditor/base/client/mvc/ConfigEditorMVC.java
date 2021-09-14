package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ConfigEditorView;

public final class ConfigEditorMVC extends SimpleMVC<ConfigEditorModel, ConfigEditorView, ConfigEditorController> {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVC();

    private ConfigEditorMVC() {
        super(ConfigEditorView::new, ConfigEditorController::new);
    }
}
