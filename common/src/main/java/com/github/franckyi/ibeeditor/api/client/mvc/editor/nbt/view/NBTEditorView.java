package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.mvc.View;
import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.EditorTagModel;

import java.util.function.Consumer;

public interface NBTEditorView extends View {
    TreeView<EditorTagModel> getTagTree();

    Button getCancelButton();

    Button getDoneButton();

    ObservableList<ButtonType> getEnabledButtons();

    void setOnButtonClick(Consumer<ButtonType> action);

    default boolean isShowAddButtons() {
        return showAddButtonsProperty().getValue();
    }

    BooleanProperty showAddButtonsProperty();

    default void setShowAddButtons(boolean value) {
        showAddButtonsProperty().setValue(value);
    }

    enum ButtonType {
        BYTE(1),
        SHORT(2),
        INT(3),
        LONG(4),
        FLOAT(5),
        DOUBLE(6),
        BYTE_ARRAY(7),
        STRING(8),
        LIST(9),
        COMPOUND(10),
        INT_ARRAY(11),
        LONG_ARRAY(12),
        MOVE_UP,
        MOVE_DOWN,
        ADD,
        DELETE,
        CUT,
        COPY,
        PASTE;

        public static final ButtonType[] CAN_DISABLE = new ButtonType[]{
                MOVE_UP,
                MOVE_DOWN,
                ADD,
                DELETE,
                CUT,
                COPY,
                PASTE
        };
        private final byte type;

        ButtonType(int type) {
            this.type = (byte) type;
        }

        ButtonType() {
            this(-1);
        }

        public byte getType() {
            return type;
        }

    }
}
