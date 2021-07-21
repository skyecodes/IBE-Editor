package com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.NBTEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.controller.NBTEditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.nbt.view.NBTEditorViewImpl;

public class NBTEditorMVCImpl implements NBTEditorMVC {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVCImpl();

    protected NBTEditorMVCImpl() {
    }

    @Override
    public NBTEditorView createView() {
        return new NBTEditorViewImpl();
    }

    @Override
    public NBTEditorController createController(NBTEditorModel model, NBTEditorView view) {
        return new NBTEditorControllerImpl(model, view);
    }
}
