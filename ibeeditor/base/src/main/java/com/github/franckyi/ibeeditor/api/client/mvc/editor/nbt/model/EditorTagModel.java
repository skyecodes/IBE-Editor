package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.node.TreeView;

public interface EditorTagModel extends TreeView.TreeItem<EditorTagModel>, Model {
    default String getName() {
        return nameProperty().getValue();
    }

    StringProperty nameProperty();

    default void setName(String value) {
        nameProperty().setValue(value);
    }

    default String getValue() {
        return valueProperty().getValue();
    }

    StringProperty valueProperty();

    default void setValue(String value) {
        valueProperty().setValue(value);
    }

    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    byte getTagType();

    boolean canBuild();

    Tag build();

    void updateValidity();

    EditorTagModel createClipboardTag();
}
