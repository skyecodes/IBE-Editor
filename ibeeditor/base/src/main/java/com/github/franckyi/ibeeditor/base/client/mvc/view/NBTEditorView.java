package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.guapi.api.node.builder.TexturedToggleButtonBuilder;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.client.mvc.NBTTagMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTTagModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class NBTEditorView extends EditorView {
    private VBox main;
    private TreeView<NBTTagModel> tagTree;
    private HBox addButtons;
    private TexturedButton addByteButton, addShortButton, addIntButton, addLongButton, addFloatButton,
            addDoubleButton, addByteArrayButton, addStringButton, addListButton, addCompoundButton,
            addIntArrayButton, addLongArrayButton, moveUpButton, moveDownButton, deleteButton,
            cutButton, copyButton, pasteButton;
    private TexturedToggleButton addButton;
    private final ObservableList<ButtonType> visibleButtons = ObservableList.create();
    private Consumer<ButtonType> onButtonClick = b -> {
    };

    @Override
    public void build() {
        super.build();
        addButtons = hBox(addBox -> {
            addBox.add(addByteButton = createAddTagButton(ButtonType.BYTE, BLUE, "Byte"));
            addBox.add(addShortButton = createAddTagButton(ButtonType.SHORT, GREEN, "Short"));
            addBox.add(addIntButton = createAddTagButton(ButtonType.INT, AQUA, "Int"));
            addBox.add(addLongButton = createAddTagButton(ButtonType.LONG, RED, "Long"));
            addBox.add(addFloatButton = createAddTagButton(ButtonType.FLOAT, LIGHT_PURPLE, "Float"));
            addBox.add(addDoubleButton = createAddTagButton(ButtonType.DOUBLE, YELLOW, "Double"));
            addBox.add(addByteArrayButton = createAddTagButton(ButtonType.BYTE_ARRAY, BLUE, "Byte Array"));
            addBox.add(addStringButton = createAddTagButton(ButtonType.STRING, GRAY, "String"));
            addBox.add(addListButton = createAddTagButton(ButtonType.LIST, GREEN, "List"));
            addBox.add(addCompoundButton = createAddTagButton(ButtonType.COMPOUND, LIGHT_PURPLE, "Compound"));
            addBox.add(addIntArrayButton = createAddTagButton(ButtonType.INT_ARRAY, AQUA, "Int Array"));
            addBox.add(addLongArrayButton = createAddTagButton(ButtonType.LONG_ARRAY, RED, "Long Array"));
            addBox.spacing(2);
        });
        getEnabledButtons().addListener(this::updateEnabledButtons);
        addButton.activeProperty().addListener(this::updateVisibleButtons);
    }

    private void updateVisibleButtons(boolean showButtons) {
        if (showButtons) {
            main.getChildren().add(1, addButtons);
        } else {
            main.getChildren().remove(addButtons);
        }
    }

    @Override
    protected IText getHeaderLabelText() {
        return ModTexts.editorTitle("NBT");
    }

    @Override
    protected Node createMain() {
        return main = (VBox) super.createMain();
    }

    @Override
    protected Node createButtonBar() {
        return hBox(buttons -> {
            buttons.add(hBox(base -> {
                base.add(moveUpButton = createButtonFromType(ButtonType.MOVE_UP).disable());
                base.add(moveDownButton = createButtonFromType(ButtonType.MOVE_DOWN).disable());
                base.add(addButton = createToggleButtonFromType(ButtonType.ADD).disable());
                base.add(deleteButton = createButtonFromType(ButtonType.DELETE).disable());
                base.spacing(2);
            }));
            buttons.add(hBox(copyBox -> {
                copyBox.add(cutButton = createButtonFromType(ButtonType.CUT).disable());
                copyBox.add(copyButton = createButtonFromType(ButtonType.COPY).disable());
                copyBox.add(pasteButton = createButtonFromType(ButtonType.PASTE).disable());
                copyBox.spacing(2);
            }), 1);
            buttons.add(hBox(expand -> {
                expand.add(createButton(ModTextures.COLLAPSE, ModTexts.COLLAPSE).action(this::collapseAll));
                expand.add(createButton(ModTextures.EXPAND, ModTexts.EXPAND).action(this::expandAll));
                expand.spacing(2);
            }));
            buttons.add(createButton(ModTextures.SCROLL_FOCUSED, ModTexts.SCROLL_FOCUSED).action(() -> tagTree.setScrollTo(getTagTree().getFocusedElement())));
            buttons.add(super.createButtonBar());
            buttons.spacing(20).prefHeight(16);
        });
    }

    @Override
    protected Node createEditor() {
        return tagTree = treeView(NBTTagModel.class).showRoot().itemHeight(20).childrenFocusable().padding(5).renderer(item -> mvc(NBTTagMVC.INSTANCE, item));
    }

    private void expandAll() {
        getTagTree().getRoot().flattened().forEach(tagModel -> tagModel.setExpanded(true));
        getTagTree().getRoot().setChildrenChanged(true);
    }

    private void collapseAll() {
        getTagTree().getRoot().flattened().forEach(tagModel -> tagModel.setExpanded(false));
        getTagTree().getRoot().setChildrenChanged(true);
    }

    public TreeView<NBTTagModel> getTagTree() {
        return tagTree;
    }

    public ObservableList<ButtonType> getEnabledButtons() {
        return visibleButtons;
    }

    public void setOnButtonClick(Consumer<ButtonType> action) {
        this.onButtonClick = action;
    }

    public Toggle getAddTagButton() {
        return addButton;
    }

    private TexturedButtonBuilder createButtonFromType(ButtonType type) {
        return createButtonFromType(type, type.getText());
    }

    private TexturedButtonBuilder createButtonFromType(ButtonType type, IText tooltipText) {
        return createButton(type.getTextureId(), tooltipText).action(() -> onButtonClick.accept(type));
    }

    private TexturedToggleButtonBuilder createToggleButtonFromType(ButtonType type) {
        return texturedToggleButton(type.getTextureId(), 16, 16, false)
                .tooltip(type.getText()).action(() -> onButtonClick.accept(type));
    }

    private TexturedButtonBuilder createAddTagButton(ButtonType type, String color, String with) {
        return createButtonFromType(type, ModTexts.addTag(color, with));
    }

    private void updateEnabledButtons() {
        for (ButtonType type : ButtonType.CAN_DISABLE) {
            getButton(type).setDisable(!getEnabledButtons().contains(type));
        }
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
            case COMPOUND:
                return addCompoundButton;
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
            case CUT:
                return cutButton;
            case COPY:
                return copyButton;
            case PASTE:
                return pasteButton;
        }
        return null;
    }

    public enum ButtonType {
        BYTE(ModTextures.BYTE_TAG_ADD, ITag.BYTE_ID),
        SHORT(ModTextures.SHORT_TAG_ADD, ITag.SHORT_ID),
        INT(ModTextures.INT_TAG_ADD, ITag.INT_ID),
        LONG(ModTextures.LONG_TAG_ADD, ITag.LONG_ID),
        FLOAT(ModTextures.FLOAT_TAG_ADD, ITag.FLOAT_ID),
        DOUBLE(ModTextures.DOUBLE_TAG_ADD, ITag.DOUBLE_ID),
        BYTE_ARRAY(ModTextures.BYTE_ARRAY_TAG_ADD, ITag.BYTE_ARRAY_ID),
        STRING(ModTextures.STRING_TAG_ADD, ITag.STRING_ID),
        LIST(ModTextures.LIST_TAG_ADD, ITag.LIST_ID),
        COMPOUND(ModTextures.COMPOUND_TAG_ADD, ITag.COMPOUND_ID),
        INT_ARRAY(ModTextures.INT_ARRAY_TAG_ADD, ITag.INT_ARRAY_ID),
        LONG_ARRAY(ModTextures.LONG_ARRAY_TAG_ADD, ITag.LONG_ARRAY_ID),
        MOVE_UP(ModTextures.MOVE_UP, ModTexts.MOVE_UP),
        MOVE_DOWN(ModTextures.MOVE_DOWN, ModTexts.MOVE_DOWN),
        ADD(ModTextures.ADD, ModTexts.ADD),
        DELETE(ModTextures.REMOVE, ModTexts.REMOVE),
        CUT(ModTextures.CUT, ModTexts.CUT),
        COPY(ModTextures.COPY, ModTexts.COPY),
        PASTE(ModTextures.PASTE, ModTexts.PASTE);

        public static final ButtonType[] CAN_DISABLE = new ButtonType[]{
                MOVE_UP,
                MOVE_DOWN,
                ADD,
                DELETE,
                CUT,
                COPY,
                PASTE
        };
        private final IIdentifier textureId;
        private final byte type;
        private final IText text;

        ButtonType(IIdentifier textureId, int type, IText text) {
            this.textureId = textureId;
            this.type = (byte) type;
            this.text = text;
        }

        ButtonType(IIdentifier textureId, int type) {
            this(textureId, type, null);
        }

        ButtonType(IIdentifier textureId, IText text) {
            this(textureId, -1, text);
        }

        public byte getType() {
            return type;
        }

        public IIdentifier getTextureId() {
            return textureId;
        }

        public IText getText() {
            return text;
        }
    }
}
