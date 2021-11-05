package com.github.franckyi.ibeeditor.client.config.gui;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class ConfigEditorMVC extends SimpleMVC<ConfigEditorModel, ConfigEditorView, ConfigEditorController> {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVC();

    private ConfigEditorMVC() {
        super(ConfigEditorView::new, ConfigEditorController::new);
    }
}
