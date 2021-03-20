package com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface NBTEditorModel {
    default TagModel getRootTag() {
        return rootTagProperty().getValue();
    }

    ObjectProperty<TagModel> rootTagProperty();

    default void setRootTag(TagModel value) {
        rootTagProperty().setValue(value);
    }

    default TagModel getClipboardTag() {
        return clipboardTagProperty().getValue();
    }

    ObjectProperty<TagModel> clipboardTagProperty();

    default void setClipboardTag(TagModel value) {
        clipboardTagProperty().setValue(value);
    }

    Text getDisabledTooltip();

    default boolean canSave() {
        return getDisabledTooltip() == null;
    }

    void apply();
}
