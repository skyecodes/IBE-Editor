package com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface StandardEditorModel extends ListEditorModel {
    Text getDisabledTooltip();

    default boolean isDisabled() {
        return getDisabledTooltip() != null;
    }

    String getTitle();
}
