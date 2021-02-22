package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.gamehooks.util.common.tag.Tag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;

import java.util.ArrayList;
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
        view.getTagTree().focusedElementProperty().addListener(newVal -> {
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
                            buttons.add(NBTEditorView.ButtonType.fromTagType(newVal.getChildren().get(0).getTagType()));
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
            view.getVisibleButtons().setAll(buttons);
        });
        view.setOnButtonClick(type -> {
            TagModel tag = view.getTagTree().getFocusedElement();
            TagModel parent = tag.getParent();
            switch (type) {
                case BYTE:
                    break;
                case SHORT:
                    break;
                case INT:
                    break;
                case LONG:
                    break;
                case FLOAT:
                    break;
                case DOUBLE:
                    break;
                case BYTE_ARRAY:
                    break;
                case STRING:
                    break;
                case LIST:
                    break;
                case OBJECT:
                    break;
                case INT_ARRAY:
                    break;
                case LONG_ARRAY:
                    break;
                case MOVE_UP:
                    break;
                case MOVE_DOWN:
                    break;
                case ADD:
                    break;
                case DELETE:
                    break;
            }
        });
    }
}
