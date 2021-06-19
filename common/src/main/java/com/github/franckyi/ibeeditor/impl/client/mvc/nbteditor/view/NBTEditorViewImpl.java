package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.view;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.node.*;
import com.github.franckyi.guapi.api.node.builder.TexturedButtonBuilder;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.EditorScreenHandler;
import com.github.franckyi.ibeeditor.impl.client.mvc.IBEEditorMVC;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class NBTEditorViewImpl implements NBTEditorView {
    private final VBox root;
    private VBox main;
    private TreeView<TagModel> tagTree;
    private Button doneButton;
    private Button cancelButton;
    private final HBox addButtons;
    private TexturedButton addByteButton, addShortButton, addIntButton, addLongButton, addFloatButton,
            addDoubleButton, addByteArrayButton, addStringButton, addListButton, addCompoundButton,
            addIntArrayButton, addLongArrayButton, rawButton, moveUpButton, moveDownButton, addButton, deleteButton,
            cutButton, copyButton, pasteButton, zoomResetButton, zoomOutButton, zoomInButton;
    private Label zoomLabel;
    private final ObservableList<ButtonType> visibleButtons = DataBindings.getObservableListFactory().createObservableArrayList();
    private Consumer<ButtonType> onButtonClick;
    private final BooleanProperty showAddButtonsProperty = DataBindings.getPropertyFactory().createBooleanProperty();

    public NBTEditorViewImpl() {
        root = vBox(root -> {
            root.spacing(5).align(CENTER).padding(5).fillWidth();
            root.add(hBox(header -> {
                header.add(hBox().prefWidth(16));
                header.add(label(translated("ibeeditor.gui.nbt_editor").aqua().bold(), true).textAlign(CENTER).prefHeight(20), 1);
                header.add(createButton("ibeeditor:textures/gui/settings.png", "ibeeditor.gui.settings").action(EditorScreenHandler::openSettings));
            }));
            root.add(main = vBox(main -> {
                main.add(hBox(buttons -> {
                    buttons.add(rawButton = createButton("ibeeditor:textures/gui/raw_nbt.png", "ibeeditor.gui.switch_raw_nbt_editor"));
                    buttons.add(hBox(base -> {
                        base.add(moveUpButton = createButtonFromType(ButtonType.MOVE_UP, "ibeeditor:textures/gui/move_up.png", "ibeeditor.gui.move_up").disable());
                        base.add(moveDownButton = createButtonFromType(ButtonType.MOVE_DOWN, "ibeeditor:textures/gui/move_down.png", "ibeeditor.gui.move_down").disable());
                        base.add(addButton = createButtonFromType(ButtonType.ADD, "ibeeditor:textures/gui/add.png", translated("ibeeditor.gui.add").green()).imageHeight(32).disable());
                        base.add(deleteButton = createButtonFromType(ButtonType.DELETE, "ibeeditor:textures/gui/delete.png", translated("ibeeditor.gui.remove").red()).disable());
                        base.spacing(2);
                    }));
                    buttons.add(hBox(copyBox -> {
                        copyBox.add(cutButton = createButtonFromType(ButtonType.CUT, "ibeeditor:textures/gui/cut.png", "ibeeditor.gui.cut").disable());
                        copyBox.add(copyButton = createButtonFromType(ButtonType.COPY, "ibeeditor:textures/gui/copy.png", "ibeeditor.gui.copy").disable());
                        copyBox.add(pasteButton = createButtonFromType(ButtonType.PASTE, "ibeeditor:textures/gui/paste.png", "ibeeditor.gui.paste").disable());
                        copyBox.spacing(2);
                    }), 1);
                    buttons.add(hBox(expand -> {
                        expand.add(createButton("ibeeditor:textures/gui/collapse_all.png", "ibeeditor.gui.collapse").action(this::collapseAll));
                        expand.add(createButton("ibeeditor:textures/gui/expand_all.png", "ibeeditor.gui.expand").action(this::expandAll));
                        expand.spacing(2);
                    }));
                    buttons.add(createButton("ibeeditor:textures/gui/scroll_focused.png", "ibeeditor.gui.scroll_focused").action(() -> tagTree.setScrollTo(getTagTree().getFocusedElement())));
                    buttons.add(hBox(zoomBox -> {
                        zoomBox.add(zoomResetButton = createButton("ibeeditor:textures/gui/zoom_reset.png", "ibeeditor.gui.zoom_reset").action(Minecraft.getClient().getScreenScaling()::restoreScale));
                        zoomBox.add(zoomOutButton = createButton("ibeeditor:textures/gui/zoom_out.png", "ibeeditor.gui.zoom_out").action(Minecraft.getClient().getScreenScaling()::scaleDown));
                        zoomBox.add(zoomLabel = label().prefWidth(25).textAlign(CENTER).padding(0, 3));
                        zoomBox.add(zoomInButton = createButton("ibeeditor:textures/gui/zoom_in.png", "ibeeditor.gui.zoom_in").action(Minecraft.getClient().getScreenScaling()::scaleUp));
                        zoomBox.fillHeight().spacing(2);
                    }));
                    buttons.spacing(20).prefHeight(16);
                }));
                main.add(tagTree = treeView(TagModel.class).showRoot().itemHeight(20).childrenFocusable().padding(5).renderer(item -> mvc(IBEEditorMVC.NBT_TAG, item)), 1);
                main.spacing(2).fillWidth();
            }), 1);
            root.add(hBox(footer -> {
                footer.spacing(20).align(CENTER);
                footer.add(doneButton = button(translated("gui.done").green()).prefWidth(90));
                footer.add(cancelButton = button(translated("gui.cancel").red()).prefWidth(90));
            }));
        });
        addButtons = hBox(addBox -> {
            addBox.add(addByteButton = createAddTagButton(ButtonType.BYTE, "ibeeditor:textures/gui/byte_tag_add.png", BLUE, "Byte"));
            addBox.add(addShortButton = createAddTagButton(ButtonType.SHORT, "ibeeditor:textures/gui/short_tag_add.png", GREEN, "Short"));
            addBox.add(addIntButton = createAddTagButton(ButtonType.INT, "ibeeditor:textures/gui/int_tag_add.png", AQUA, "Int"));
            addBox.add(addLongButton = createAddTagButton(ButtonType.LONG, "ibeeditor:textures/gui/long_tag_add.png", RED, "Long"));
            addBox.add(addFloatButton = createAddTagButton(ButtonType.FLOAT, "ibeeditor:textures/gui/float_tag_add.png", LIGHT_PURPLE, "Float"));
            addBox.add(addDoubleButton = createAddTagButton(ButtonType.DOUBLE, "ibeeditor:textures/gui/double_tag_add.png", YELLOW, "Double"));
            addBox.add(addByteArrayButton = createAddTagButton(ButtonType.BYTE_ARRAY, "ibeeditor:textures/gui/byte_array_tag_add.png", BLUE, "Byte Array"));
            addBox.add(addStringButton = createAddTagButton(ButtonType.STRING, "ibeeditor:textures/gui/string_tag_add.png", GRAY, "String"));
            addBox.add(addListButton = createAddTagButton(ButtonType.LIST, "ibeeditor:textures/gui/list_tag_add.png", GREEN, "List"));
            addBox.add(addCompoundButton = createAddTagButton(ButtonType.COMPOUND, "ibeeditor:textures/gui/compound_tag_add.png", LIGHT_PURPLE, "Compound"));
            addBox.add(addIntArrayButton = createAddTagButton(ButtonType.INT_ARRAY, "ibeeditor:textures/gui/int_array_tag_add.png", AQUA, "Int Array"));
            addBox.add(addLongArrayButton = createAddTagButton(ButtonType.LONG_ARRAY, "ibeeditor:textures/gui/long_array_tag_add.png", RED, "Long Array"));
            addBox.spacing(2);
        });
        getEnabledButtons().addListener(this::updateButtons);
        showAddButtonsProperty().addListener(newVal -> {
            if (newVal) {
                main.getChildren().add(1, addButtons);
                addButton.setImageY(16);
            } else {
                main.getChildren().remove(addButtons);
                addButton.setImageY(0);
            }
        });
        Minecraft.getClient().getScreenScaling().scaleProperty().addListener(this::onZoomUpdated);
        zoomResetButton.disableProperty().bind(Minecraft.getClient().getScreenScaling().canScaleBeResetProperty().not());
        onZoomUpdated();
    }

    private void expandAll() {
        getTagTree().getRoot().flattened().forEach(tagModel -> tagModel.setExpanded(true));
        getTagTree().getRoot().setChildrenChanged(true);
    }

    private void collapseAll() {
        getTagTree().getRoot().flattened().forEach(tagModel -> tagModel.setExpanded(false));
        getTagTree().getRoot().setChildrenChanged(true);
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
    public ObservableList<ButtonType> getEnabledButtons() {
        return visibleButtons;
    }

    @Override
    public void setOnButtonClick(Consumer<ButtonType> action) {
        this.onButtonClick = action;
    }

    @Override
    public BooleanProperty showAddButtonsProperty() {
        return showAddButtonsProperty;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    private TexturedButtonBuilder createButton(String id, String tooltipText) {
        return createButton(id, translated(tooltipText));
    }

    private TexturedButtonBuilder createButton(String id, Text tooltipText) {
        return texturedButton(id, 16, 16, false)
                .prefSize(16, 16)
                .tooltip(tooltipText);
    }

    private TexturedButtonBuilder createButtonFromType(ButtonType type, String id, String tooltipText) {
        return createButtonFromType(type, id, translated(tooltipText));
    }

    private TexturedButtonBuilder createButtonFromType(ButtonType type, String id, Text tooltipText) {
        return createButton(id, tooltipText).action(() -> {
            if (onButtonClick != null) {
                onButtonClick.accept(type);
            }
        });
    }

    private TexturedButtonBuilder createAddTagButton(ButtonType type, String id, String color, String with) {
        return createButtonFromType(type, id, translated("ibeeditor.gui.add_tag").color(color).with(text(with)));
    }

    private void updateButtons() {
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

    private void onZoomUpdated() {
        zoomOutButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleDown());
        zoomLabel.setLabel(translated(Minecraft.getClient().getScreenScaling().getScale() == 0 ? "options.guiScale.auto" : Integer.toString(Minecraft.getClient().getScreenScaling().getScale())));
        zoomInButton.setDisable(!Minecraft.getClient().getScreenScaling().canScaleUp());
    }
}
