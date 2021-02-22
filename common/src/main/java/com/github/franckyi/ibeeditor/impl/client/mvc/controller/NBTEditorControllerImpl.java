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
        view.getTagTree().rootItemProperty().bind(model.tagProperty());
        view.getDoneButton().onAction(event -> GUAPI.getScreenHandler().hide());
        view.getCancelButton().onAction(event -> GUAPI.setDebugMode(!GUAPI.isDebugMode()));
        view.getTagTree().focusedElementProperty().addListener(newVal -> updateButtons(newVal, view));
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
                    updateButtons(tag, view);
                    break;
                case MOVE_DOWN:
                    int index1 = parent.getChildren().indexOf(tag);
                    Collections.swap(parent.getChildren(), index1, index1 + 1);
                    updateButtons(tag, view);
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
                case DELETE:
                    parent.getChildren().remove(tag);
                    view.getTagTree().setFocusedElement(null);
                    break;
            }
        });
    }

    private void updateButtons(TagModel newVal, NBTEditorView view) {
        List<NBTEditorView.ButtonType> buttons = new ArrayList<>();
        if (newVal != null) {
            switch (newVal.getTagType()) {
                case Tag.COMPOUND_ID:
                    buttons.addAll(NBTEditorView.ButtonType.ALL_ADD_TAG);
                    break;
                case Tag.BYTE_ARRAY_ID:
                case Tag.INT_ARRAY_ID:
                case Tag.LONG_ARRAY_ID:
                    buttons.add(NBTEditorView.ButtonType.ADD);
                    break;
                case Tag.LIST_ID:
                    if (newVal.getChildren().isEmpty()) {
                        buttons.addAll(NBTEditorView.ButtonType.ALL_ADD_TAG);
                    } else {
                        buttons.add(NBTEditorView.ButtonType.ADD);
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
            }
        }
        view.getEnabledButtons().setAll(buttons);
    }

    private void addChildTag(NBTEditorView view, TagModel parent, Tag<?> newTag) {
        addChildTag(view, parent, new TagModelImpl(newTag, parent, parent.getTagType() != Tag.LIST_ID ? "" : null, null));
    }

    private void addChildTag(NBTEditorView view, TagModel parent, byte type, String value) {
        addChildTag(view, parent, new TagModelImpl(type, parent, value));
    }

    private void addChildTag(NBTEditorView view, TagModel parent, TagModel tag) {
        parent.getChildren().add(tag);
        parent.setExpanded(true);
        view.getTagTree().setScrollTo(tag);
        view.getTagTree().setFocusedElement(tag);
    }
}
