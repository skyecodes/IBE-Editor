package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.ObjectProperty;

public interface NBTEditorModel {
    default TagModel getTag() {
        return tagProperty().getValue();
    }

    ObjectProperty<TagModel> tagProperty();

    default void setTag(TagModel value) {
        tagProperty().setValue(value);
    }

    void apply();
}
