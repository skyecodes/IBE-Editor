package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.controller;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.tag.Tag;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.model.TagModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBTEditorControllerImpl extends AbstractController<NBTEditorModel, NBTEditorView> implements NBTEditorController {
    public NBTEditorControllerImpl(NBTEditorModel model, NBTEditorView view) {
        super(model, view);
    }

    @Override
    public void init() {
        view.getTagTree().rootItemProperty().bind(model.rootTagProperty());
        view.getDoneButton().onAction(event -> model.apply());
        view.getCancelButton().onAction(event -> GameHooks.client().getScreenHandler().hideScene());
        view.getTagTree().focusedElementProperty().addListener(this::updateButtons);
        view.setOnButtonClick(type -> {
            TagModel tag = view.getTagTree().getFocusedElement();
            TagModel parent = tag.getParent();
            switch (type) {
                case BYTE:
                    addChildTag(tag, new ByteTag());
                    break;
                case SHORT:
                    addChildTag(tag, new ShortTag());
                    break;
                case INT:
                    addChildTag(tag, new IntTag());
                    break;
                case LONG:
                    addChildTag(tag, new LongTag());
                    break;
                case FLOAT:
                    addChildTag(tag, new FloatTag());
                    break;
                case DOUBLE:
                    addChildTag(tag, new DoubleTag());
                    break;
                case BYTE_ARRAY:
                    addChildTag(tag, new ByteArrayTag());
                    break;
                case STRING:
                    addChildTag(tag, new StringTag());
                    break;
                case LIST:
                    addChildTag(tag, new ArrayTag());
                    break;
                case OBJECT:
                    addChildTag(tag, new ObjectTag());
                    break;
                case INT_ARRAY:
                    addChildTag(tag, new IntArrayTag());
                    break;
                case LONG_ARRAY:
                    addChildTag(tag, new LongArrayTag());
                    break;
                case MOVE_UP:
                    int index0 = parent.getChildren().indexOf(tag);
                    Collections.swap(parent.getChildren(), index0, index0 - 1);
                    updateButtons(tag);
                    break;
                case MOVE_DOWN:
                    int index1 = parent.getChildren().indexOf(tag);
                    Collections.swap(parent.getChildren(), index1, index1 + 1);
                    updateButtons(tag);
                    break;
                case ADD:
                    switch (tag.getTagType()) {
                        case Tag.BYTE_ARRAY_ID:
                            addChildTag(tag, Tag.BYTE_ID, "0");
                            break;
                        case Tag.INT_ARRAY_ID:
                            addChildTag(tag, Tag.INT_ID, "0");
                            break;
                        case Tag.LONG_ARRAY_ID:
                            addChildTag(tag, Tag.LONG_ID, "0");
                            break;
                        case Tag.LIST_ID:
                            if (tag.getChildren().isEmpty()) {
                                view.setShowAddButtons(!view.isShowAddButtons());
                            } else {
                                addChildTag(tag, GameHooks.common().tagFactory().create(tag.getChildren().get(0).getTagType()));
                            }
                            break;
                        case Tag.COMPOUND_ID:
                            view.setShowAddButtons(!view.isShowAddButtons());
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
                    updateButtons(tag);
                    break;
                case PASTE:
                    TagModel clipboardTag = model.getClipboardTag();
                    if (clipboardTag.canBuild()) {
                        addChildTag(tag, clipboardTag.build(), clipboardTag.getName());
                    } else {
                        addChildTag(tag, clipboardTag.getTagType(), clipboardTag.getValue());
                    }
                    break;
            }
        });
        view.getDoneButton().disableProperty().bind(model.rootTagProperty().bindMapToBoolean(TagModel::validProperty).not());
    }

    private void updateButtons(TagModel newVal) {
        view.setShowAddButtons(false);
        List<NBTEditorView.ButtonType> buttons = new ArrayList<>();
        TagModel clipboardTag = model.getClipboardTag();
        if (newVal != null) {
            switch (newVal.getTagType()) {
                case Tag.COMPOUND_ID:
                    buttons.add(NBTEditorView.ButtonType.ADD);
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

    private void addChildTag(TagModel parent, Tag newTag) {
        addChildTag(parent, newTag, "");
    }

    private void addChildTag(TagModel parent, Tag newTag, String name) {
        addChildTag(parent, new TagModelImpl(newTag, parent, parent.getTagType() != Tag.LIST_ID ? name : null, null));
    }

    private void addChildTag(TagModel parent, byte type, String value) {
        addChildTag(parent, new TagModelImpl(type, parent, value));
    }

    private void addChildTag(TagModel parent, TagModel tag) {
        parent.getChildren().add(tag);
        parent.setExpanded(true);
        view.getTagTree().setScrollTo(tag);
        view.getTagTree().setFocusedElement(tag);
    }
}
