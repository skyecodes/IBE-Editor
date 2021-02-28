package com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gamehooks.api.common.tag.Tag;
import com.github.franckyi.guapi.api.node.TreeView;

public interface TagModel extends TreeView.TreeItem<TagModel> {
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

    TagModel createClipboardTag();
}
