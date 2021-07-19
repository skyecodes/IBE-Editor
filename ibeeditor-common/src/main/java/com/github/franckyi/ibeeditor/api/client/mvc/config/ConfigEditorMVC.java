package com.github.franckyi.ibeeditor.api.client.mvc.config;

import com.github.franckyi.ibeeditor.api.client.mvc.base.ListEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.config.controller.ConfigEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.view.ConfigEditorView;

public interface ConfigEditorMVC extends ListEditorMVC<ConfigEditorModel, ConfigEditorView, ConfigEditorController> {
}
