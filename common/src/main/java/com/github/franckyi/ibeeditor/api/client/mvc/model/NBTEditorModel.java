package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.ObjectProperty;

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

    void apply();
}
