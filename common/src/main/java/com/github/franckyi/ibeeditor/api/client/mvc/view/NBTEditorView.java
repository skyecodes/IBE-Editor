package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.gamehooks.util.common.tag.Tag;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public interface NBTEditorView extends View {
    Label getHeaderLabel();

    TreeView<TagModel> getTagTree();

    Button getDoneButton();

    Button getCancelButton();

    ObservableList<ButtonType> getEnabledButtons();

    void setOnButtonClick(Consumer<ButtonType> action);

    enum ButtonType {
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BYTE_ARRAY,
        STRING,
        LIST,
        OBJECT,
        INT_ARRAY,
        LONG_ARRAY,
        MOVE_UP,
        MOVE_DOWN,
        ADD,
        DELETE;

        public static final List<ButtonType> ALL_ADD_TAG = Arrays.asList(
                BYTE,
                SHORT,
                INT,
                LONG,
                FLOAT,
                DOUBLE,
                BYTE_ARRAY,
                STRING,
                LIST,
                OBJECT,
                INT_ARRAY,
                LONG_ARRAY
        );

        public static ButtonType fromTagType(byte type) {
            switch (type) {
                case Tag.BYTE_ID:
                    return BYTE;
                case Tag.SHORT_ID:
                    return SHORT;
                case Tag.INT_ID:
                    return INT;
                case Tag.LONG_ID:
                    return LONG;
                case Tag.FLOAT_ID:
                    return FLOAT;
                case Tag.DOUBLE_ID:
                    return DOUBLE;
                case Tag.BYTE_ARRAY_ID:
                    return BYTE_ARRAY;
                case Tag.STRING_ID:
                    return STRING;
                case Tag.LIST_ID:
                    return LIST;
                case Tag.COMPOUND_ID:
                    return OBJECT;
                case Tag.INT_ARRAY_ID:
                    return INT_ARRAY;
                case Tag.LONG_ARRAY_ID:
                    return LONG_ARRAY;
            }
            return null;
        }
    }
}
