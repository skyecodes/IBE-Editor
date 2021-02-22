package com.github.franckyi.ibeeditor.impl.client.mvc.view;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.gamehooks.util.common.text.TextFormatting;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public class NBTEditorViewImpl implements NBTEditorView {
    private final VBox root;
    private HBox buttonBox;
    private Label headerLabel;
    private TreeView<TagModel> tagTree;
    private Button doneButton;
    private Button cancelButton;
    private final TexturedButton addByteButton, addShortButton, addIntButton, addLongButton, addFloatButton,
            addDoubleButton, addByteArrayButton, addStringButton, addListButton, addObjectButton,
            addIntArrayButton, addLongArrayButton, moveUpButton, moveDownButton, addButton, deleteButton;
    private final ObservableList<ButtonType> visibleButtons = ObservableListFactory.arrayList();
    private Consumer<ButtonType> onButtonClick;

    public NBTEditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(headerLabel = label(translated("ibeeditor.gui.nbt_editor", AQUA, BOLD), true).textAlign(CENTER).prefHeight(15));
            root.add(vBox(main -> {
                main.add(buttonBox = hBox(2).prefHeight(16));
                main.add(tagTree = treeView(TagModel.class).showRoot().itemHeight(20).childrenFocusable().padding(5).renderer(item -> mvc(TagView.class, item)), 1);
                main.spacing(2).fillWidth();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translated("gui.done", GREEN)).prefWidth(90));
                footer.add(cancelButton = button(translated("gui.cancel", RED)).prefWidth(90));
            }));
        });
        addByteButton = createButton(ButtonType.BYTE, "ibeeditor:textures/gui/byte_tag_add.png", "Add Byte Tag", DARK_BLUE);
        addShortButton = createButton(ButtonType.SHORT, "ibeeditor:textures/gui/short_tag_add.png", "Add Short Tag", DARK_GREEN);
        addIntButton = createButton(ButtonType.INT, "ibeeditor:textures/gui/int_tag_add.png", "Add Int Tag", DARK_AQUA);
        addLongButton = createButton(ButtonType.LONG, "ibeeditor:textures/gui/long_tag_add.png", "Add Long Tag", DARK_RED);
        addFloatButton = createButton(ButtonType.FLOAT, "ibeeditor:textures/gui/float_tag_add.png", "Add Float Tag", DARK_PURPLE);
        addDoubleButton = createButton(ButtonType.DOUBLE, "ibeeditor:textures/gui/double_tag_add.png", "Add Double Tag", GOLD);
        addByteArrayButton = createButton(ButtonType.BYTE_ARRAY, "ibeeditor:textures/gui/byte_array_tag_add.png", "Add Byte Array Tag", BLUE);
        addStringButton = createButton(ButtonType.STRING, "ibeeditor:textures/gui/string_tag_add.png", "Add String Tag", GRAY);
        addListButton = createButton(ButtonType.LIST, "ibeeditor:textures/gui/list_tag_add.png", "Add List Tag", GREEN);
        addObjectButton = createButton(ButtonType.OBJECT, "ibeeditor:textures/gui/object_tag_add.png", "Add Compound Tag", LIGHT_PURPLE);
        addIntArrayButton = createButton(ButtonType.INT_ARRAY, "ibeeditor:textures/gui/int_array_tag_add.png", "Add Int Array Tag", AQUA);
        addLongArrayButton = createButton(ButtonType.LONG_ARRAY, "ibeeditor:textures/gui/long_array_tag_add.png", "Add Long Array Tag", RED);
        moveUpButton = createButton(ButtonType.MOVE_UP, "ibeeditor:textures/gui/move_up.png", "Move Up", YELLOW);
        moveDownButton = createButton(ButtonType.MOVE_DOWN, "ibeeditor:textures/gui/move_down.png", "Move Down", YELLOW);
        addButton = createButton(ButtonType.ADD, "ibeeditor:textures/gui/add.png", "Add", GREEN);
        deleteButton = createButton(ButtonType.DELETE, "ibeeditor:textures/gui/delete.png", "Delete", RED);
        getVisibleButtons().addListener(this::updateButtons);
    }

    @Override
    public Label getHeaderLabel() {
        return headerLabel;
    }

    @Override
    public TreeView<TagModel> getTagTree() {
        return tagTree;
    }

    @Override
    public Button getDoneButton() {
        return doneButton;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public ObservableList<ButtonType> getVisibleButtons() {
        return visibleButtons;
    }

    @Override
    public void setOnButtonClick(Consumer<ButtonType> action) {
        this.onButtonClick = action;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private TexturedButton createButton(ButtonType type, String id, String tooltipText, TextFormatting... tooltipFormatting) {
        return texturedButton(id, 16, 16, false)
                .prefSize(16, 16)
                .tooltip(tooltipText, tooltipFormatting)
                .action(() -> {
                    if (onButtonClick != null) {
                        onButtonClick.accept(type);
                    }
                });
    }

    private void updateButtons() {
        buttonBox.getChildren().setAll(getVisibleButtons().stream().map(this::getButton).collect(Collectors.toList()));
    }

    private TexturedButton getButton(ButtonType buttonType) {
        switch (buttonType) {
            case BYTE:
                return addByteButton;
            case SHORT:
                return addShortButton;
            case INT:
                return addIntButton;
            case LONG:
                return addLongButton;
            case FLOAT:
                return addFloatButton;
            case DOUBLE:
                return addDoubleButton;
            case BYTE_ARRAY:
                return addByteArrayButton;
            case STRING:
                return addStringButton;
            case LIST:
                return addListButton;
            case OBJECT:
                return addObjectButton;
            case INT_ARRAY:
                return addIntArrayButton;
            case LONG_ARRAY:
                return addLongArrayButton;
            case MOVE_UP:
                return moveUpButton;
            case MOVE_DOWN:
                return moveDownButton;
            case ADD:
                return addButton;
            case DELETE:
                return deleteButton;
        }
        return null;
    }
}
