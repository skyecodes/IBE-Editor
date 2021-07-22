package com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;

public interface StandardEditorModel extends ListEditorModel {
    Text getDisabledTooltip();

    default boolean isDisabled() {
        return getDisabledTooltip() != null;
    }

    String getTitle();
}
