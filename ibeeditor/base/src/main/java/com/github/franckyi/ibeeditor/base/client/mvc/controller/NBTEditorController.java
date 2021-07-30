package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.api.common.tag.Tag;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.guapi.api.mvc.Controller;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.NBTTagModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.NBTEditorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBTEditorController extends AbstractController<NBTEditorModel, NBTEditorView> implements Controller<NBTEditorModel, NBTEditorView> {
    public NBTEditorController(NBTEditorModel model, NBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getTagTree().rootItemProperty().bind(model.rootTagProperty());
        if (model.canSave()) {
            view.getDoneButton().disableProperty().bind(model.rootTagProperty().bindMapToBoolean(NBTTagModel::validProperty).not());
            view.getDoneButton().onAction(event -> model.apply());
        } else {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }
        view.getCancelButton().onAction(event -> Guapi.getScreenHandler().hideScene());
        view.getTagTree().focusedElementProperty().addListener(this::updateEnabledButtons);
        view.setOnButtonClick(this::onButtonClick);
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
                addChildTag(tag, Tag.fromType(target.getType()));
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
                        if (!tag.getChildren().isEmpty()) {
                            addChildTag(tag, Tag.fromType(tag.getChildren().get(0).getTagType()));
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
        addChildTag(parent, new NBTTagModel(newTag, parent, parent.getTagType() != Tag.LIST_ID ? name : null, null));
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
}
