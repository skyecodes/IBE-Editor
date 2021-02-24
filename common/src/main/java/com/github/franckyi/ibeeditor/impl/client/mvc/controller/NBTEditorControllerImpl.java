package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.gamehooks.util.common.tag.*;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.TagModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBTEditorControllerImpl implements NBTEditorController {
    public static final NBTEditorController INSTANCE = new NBTEditorControllerImpl();

    protected NBTEditorControllerImpl() {
    }

    @Override
    public void init(NBTEditorModel model, NBTEditorView view) {
        view.getTagTree().rootItemProperty().bind(model.rootTagProperty());
        view.getDoneButton().onAction(event -> model.apply());
        view.getCancelButton().onAction(event -> GUAPI.getScreenHandler().hideScene());
        view.getTagTree().focusedElementProperty().addListener(newVal -> updateButtons(newVal, model, view));
        view.setOnButtonClick(type -> {
            TagModel tag = view.getTagTree().getFocusedElement();
            TagModel parent = tag.getParent();
            switch (type) {
                case BYTE:
                    addChildTag(view, tag, new ByteTag());
                    break;
                case SHORT:
                    addChildTag(view, tag, new ShortTag());
                    break;
                case INT:
                    addChildTag(view, tag, new IntTag());
                    break;
                case LONG:
                    addChildTag(view, tag, new LongTag());
                    break;
                case FLOAT:
                    addChildTag(view, tag, new FloatTag());
                    break;
                case DOUBLE:
                    addChildTag(view, tag, new DoubleTag());
                    break;
                case BYTE_ARRAY:
                    addChildTag(view, tag, new ByteArrayTag());
                    break;
                case STRING:
                    addChildTag(view, tag, new StringTag());
                    break;
                case LIST:
                    addChildTag(view, tag, new ArrayTag());
                    break;
                case OBJECT:
                    addChildTag(view, tag, new ObjectTag());
                    break;
                case INT_ARRAY:
                    addChildTag(view, tag, new IntArrayTag());
                    break;
                case LONG_ARRAY:
                    addChildTag(view, tag, new LongArrayTag());
                    break;
                case MOVE_UP:
                    int index0 = parent.getChildren().indexOf(tag);
                    Collections.swap(parent.getChildren(), index0, index0 - 1);
                    updateButtons(tag, model, view);
                    break;
                case MOVE_DOWN:
                    int index1 = parent.getChildren().indexOf(tag);
                    Collections.swap(parent.getChildren(), index1, index1 + 1);
                    updateButtons(tag, model, view);
                    break;
                case ADD:
                    switch (tag.getTagType()) {
                        case Tag.BYTE_ARRAY_ID:
                            addChildTag(view, tag, Tag.BYTE_ID, "0");
                            break;
                        case Tag.INT_ARRAY_ID:
                            addChildTag(view, tag, Tag.INT_ID, "0");
                            break;
                        case Tag.LONG_ARRAY_ID:
                            addChildTag(view, tag, Tag.LONG_ID, "0");
                            break;
                        case Tag.LIST_ID:
                            switch (tag.getChildren().get(0).getTagType()) {
                                case Tag.BYTE_ID:
                                    addChildTag(view, tag, new ByteTag());
                                    break;
                                case Tag.SHORT_ID:
                                    addChildTag(view, tag, new ShortTag());
                                    break;
                                case Tag.INT_ID:
                                    addChildTag(view, tag, new IntTag());
                                    break;
                                case Tag.LONG_ID:
                                    addChildTag(view, tag, new LongTag());
                                    break;
                                case Tag.FLOAT_ID:
                                    addChildTag(view, tag, new FloatTag());
                                    break;
                                case Tag.DOUBLE_ID:
                                    addChildTag(view, tag, new DoubleTag());
                                    break;
                                case Tag.BYTE_ARRAY_ID:
                                    addChildTag(view, tag, new ByteArrayTag());
                                    break;
                                case Tag.STRING_ID:
                                    addChildTag(view, tag, new StringTag());
                                    break;
                                case Tag.LIST_ID:
                                    addChildTag(view, tag, new ArrayTag());
                                    break;
                                case Tag.COMPOUND_ID:
                                    addChildTag(view, tag, new ObjectTag());
                                    break;
                                case Tag.INT_ARRAY_ID:
                                    addChildTag(view, tag, new IntArrayTag());
                                    break;
                                case Tag.LONG_ARRAY_ID:
                                    addChildTag(view, tag, new LongArrayTag());
                                    break;
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
                    updateButtons(tag, model, view);
                    break;
                case PASTE:
                    TagModel clipboardTag = model.getClipboardTag();
                    if (clipboardTag.canBuild()) {
                        addChildTag(view, tag, clipboardTag.build(), clipboardTag.getName());
                    } else {
                        addChildTag(view, tag, clipboardTag.getTagType(), clipboardTag.getValue());
                    }
                    break;
            }
        });
        view.getDoneButton().disableProperty().bind(model.rootTagProperty().bindMapToBoolean(TagModel::validProperty).not());
    }

    private void updateButtons(TagModel newVal, NBTEditorModel model, NBTEditorView view) {
        List<NBTEditorView.ButtonType> buttons = new ArrayList<>();
        TagModel clipboardTag = model.getClipboardTag();
        if (newVal != null) {
            switch (newVal.getTagType()) {
                case Tag.COMPOUND_ID:
                    buttons.addAll(NBTEditorView.ButtonType.ALL_ADD_TAG);
                    if (clipboardTag != null && clipboardTag.canBuild()) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    break;
                case Tag.BYTE_ARRAY_ID:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.BYTE_ID) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.INT_ARRAY_ID:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.INT_ID) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.LONG_ARRAY_ID:
                    if (clipboardTag != null && !clipboardTag.canBuild() && clipboardTag.getTagType() == Tag.LONG_ID) {
                        buttons.add(NBTEditorView.ButtonType.PASTE);
                    }
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.LIST_ID:
                    if (newVal.getChildren().isEmpty()) {
                        buttons.addAll(NBTEditorView.ButtonType.ALL_ADD_TAG);
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
                TagModel parent = newVal.getParent();
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

    private void addChildTag(NBTEditorView view, TagModel parent, Tag<?> newTag) {
        addChildTag(view, parent, newTag, "");
    }

    private void addChildTag(NBTEditorView view, TagModel parent, Tag<?> newTag, String name) {
        addChildTag(view, parent, new TagModelImpl(newTag, parent, parent.getTagType() != Tag.LIST_ID ? name : null, null));
    }

    private void addChildTag(NBTEditorView view, TagModel parent, byte type, String value) {
        addChildTag(view, parent, new TagModelImpl(type, parent, value));
    }

    private void addChildTag(NBTEditorView view, TagModel parent, TagModel tag) {
        parent.getChildren().add(tag);
        parent.setExpanded(true);
        view.getTagTree().setFocusedElement(tag);
    }
}
