package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface NBTEditorModel extends Model {
    default EditorTagModel getRootTag() {
        return rootTagProperty().getValue();
    }

    ObjectProperty<EditorTagModel> rootTagProperty();

    default void setRootTag(EditorTagModel value) {
        rootTagProperty().setValue(value);
    }

    default EditorTagModel getClipboardTag() {
        return clipboardTagProperty().getValue();
    }

    ObjectProperty<EditorTagModel> clipboardTagProperty();

    default void setClipboardTag(EditorTagModel value) {
        clipboardTagProperty().setValue(value);
    }

    Text getDisabledTooltip();

    default boolean canSave() {
        return getDisabledTooltip() == null;
    }

    void apply();
}
