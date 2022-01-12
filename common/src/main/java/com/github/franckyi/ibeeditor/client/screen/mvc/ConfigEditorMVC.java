package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.ConfigScreenView;

public final class ConfigEditorMVC extends SimpleMVC<ConfigEditorScreenModel, ConfigScreenView, ConfigEditorController> {
    public static final ConfigEditorMVC INSTANCE = new ConfigEditorMVC();

    private ConfigEditorMVC() {
        super(ConfigScreenView::new, ConfigEditorController::new);
    }
}
