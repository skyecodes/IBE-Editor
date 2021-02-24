package com.github.franckyi.ibeeditor.api.client.mvc.view;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public interface NBTEditorView extends View {
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
        DELETE,
        CUT,
        COPY,
        PASTE;

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
    }
}
