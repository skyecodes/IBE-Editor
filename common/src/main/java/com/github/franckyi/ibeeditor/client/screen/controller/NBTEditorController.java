package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.NBTTagModel;
import com.github.franckyi.ibeeditor.client.screen.view.NBTEditorView;
import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.nbt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBTEditorController extends AbstractController<NBTEditorModel, NBTEditorView> implements EditorController<NBTEditorModel, NBTEditorView> {
    public NBTEditorController(NBTEditorModel model, NBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        EditorController.super.bind();
        view.addOpenEditorButton(() -> model.changeEditor(EditorType.STANDARD));
        view.addOpenSNBTEditorButton(() -> model.changeEditor(EditorType.SNBT));
        view.getTagTree().rootItemProperty().bind(model.rootTagProperty());
        view.getTagTree().focusedElementProperty().addListener(this::updateEnabledButtons);
        view.setOnButtonClick(this::onButtonClick);
        view.getDoneButton().onAction(model::update);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
    }

    private void onButtonClick(NBTEditorView.ButtonType target) {
        NBTTagModel tag = view.getTagTree().getFocusedElement();
        NBTTagModel parent = tag.getParent();
        switch (target) {
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case BYTE_ARRAY:
            case STRING:
            case LIST:
            case COMPOUND:
            case INT_ARRAY:
            case LONG_ARRAY:
                addChildTag(tag, createEmptyTag(target.getType()));
                break;
            case MOVE_UP:
                int index0 = parent.getChildren().indexOf(tag);
                Collections.swap(parent.getChildren(), index0, index0 - 1);
                updateEnabledButtons(tag);
                break;
            case MOVE_DOWN:
                int index1 = parent.getChildren().indexOf(tag);
                Collections.swap(parent.getChildren(), index1, index1 + 1);
                updateEnabledButtons(tag);
                break;
            case ADD:
                switch (tag.getTagType()) {
                    case Tag.TAG_BYTE_ARRAY:
                        addChildTag(tag, Tag.TAG_BYTE, "0");
                        break;
                    case Tag.TAG_INT_ARRAY:
                        addChildTag(tag, Tag.TAG_INT, "0");
                        break;
                    case Tag.TAG_LONG_ARRAY:
                        addChildTag(tag, Tag.TAG_LONG, "0");
                        break;
                    case Tag.TAG_LIST:
                        if (!tag.getChildren().isEmpty()) {
                            addChildTag(tag, createEmptyTag(tag.getChildren().get(0).getTagType()));
                        }
                        break;
                }
                break;
            case CUT:
                model.setClipboardTag(tag);
            case DELETE:
                parent.getChildren().remove(tag);
                view.getTagTree().setFocusedElement(null);
                break;
            case COPY:
                model.setClipboardTag(tag.createClipboardTag());
                updateEnabledButtons(tag);
                break;
            case PASTE:
                NBTTagModel clipboardTag = model.getClipboardTag();
                if (clipboardTag.canBuild()) {
                    addChildTag(tag, clipboardTag.build(), clipboardTag.getName());
                } else {
                    addChildTag(tag, clipboardTag.getTagType(), clipboardTag.getValue());
                }
                break;
        }
    }

    private void updateEnabledButtons(NBTTagModel newVal) {
        view.getAddTagButton().setActive(false);
        List<NBTEditorView.ButtonType> buttons = new ArrayList<>();
        NBTTagModel clipboardTag = model.getClipboardTag();
        if (newVal != null) {
            switch (newVal.getTagType()) {
                case Tag.TAG_COMPOUND:
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    if (clipboardTag != null && clipboardTag.canBuild()) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    break;
                case Tag.TAG_BYTE_ARRAY:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.TAG_BYTE) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.TAG_INT_ARRAY:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.TAG_INT) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.TAG_LONG_ARRAY:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.TAG_LONG) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.TAG_LIST:
                    if (newVal.getChildren().isEmpty()) {
                        buttons.add(NBTEditorView.ButtonType.ADD);
                        if (clipboardTag != null && clipboardTag.canBuild()) {
                            buttons.add(NBTEditorView.ButtonType.PASTE);
                        }
                    } else {
                        buttons.add(NBTEditorView.ButtonType.ADD);
                        if (clipboardTag != null && clipboardTag.canBuild() && clipboardTag.getTagType() == newVal.getChildren().get(0).getTagType()) {
                            buttons.add(NBTEditorView.ButtonType.PASTE);
                        }
                    }
            }
            if (newVal.getParent() != null) {
                NBTTagModel parent = newVal.getParent();
                if (parent.getChildren().indexOf(newVal) != 0) {
                    buttons.add(NBTEditorView.ButtonType.MOVE_UP);
                }
                if (parent.getChildren().indexOf(newVal) != parent.getChildren().size() - 1) {
                    buttons.add(NBTEditorView.ButtonType.MOVE_DOWN);
                }
                buttons.add(NBTEditorView.ButtonType.DELETE);
                buttons.add(NBTEditorView.ButtonType.CUT);
            }
            buttons.add(NBTEditorView.ButtonType.COPY);
        }
        view.getEnabledButtons().setAll(buttons);
    }

    private void addChildTag(NBTTagModel parent, Tag newTag) {
        addChildTag(parent, newTag, "");
    }

    private void addChildTag(NBTTagModel parent, Tag newTag, String name) {
        addChildTag(parent, new NBTTagModel(newTag, parent, parent.getTagType() != Tag.TAG_LIST ? name : null, null));
    }

    private void addChildTag(NBTTagModel parent, byte target, String value) {
        addChildTag(parent, new NBTTagModel(target, parent, value));
    }

    private void addChildTag(NBTTagModel parent, NBTTagModel tag) {
        parent.getChildren().add(tag);
        parent.setExpanded(true);
        view.getTagTree().setScrollTo(tag);
        view.getTagTree().setFocusedElement(tag);
    }

    private Tag createEmptyTag(byte type) {
        return switch (type) {
            case Tag.TAG_BYTE -> ByteTag.ZERO;
            case Tag.TAG_SHORT -> ShortTag.valueOf((short) 0);
            case Tag.TAG_INT -> IntTag.valueOf(0);
            case Tag.TAG_LONG -> LongTag.valueOf(0);
            case Tag.TAG_FLOAT -> FloatTag.ZERO;
            case Tag.TAG_DOUBLE -> DoubleTag.ZERO;
            case Tag.TAG_BYTE_ARRAY -> new ByteArrayTag(new byte[0]);
            case Tag.TAG_STRING -> StringTag.valueOf("");
            case Tag.TAG_LIST -> new ListTag();
            case Tag.TAG_COMPOUND -> new CompoundTag();
            case Tag.TAG_INT_ARRAY -> new IntArrayTag(new int[0]);
            case Tag.TAG_LONG_ARRAY -> new LongArrayTag(new long[0]);
            default -> null;
        };
    }
}
