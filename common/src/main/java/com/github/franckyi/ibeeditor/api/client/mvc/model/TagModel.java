package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gamehooks.util.common.tag.Tag;
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

    byte getTagType();

    Tag<?> build();
}
