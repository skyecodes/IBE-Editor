package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor;

import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.NBTEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.controller.NBTEditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.view.NBTEditorViewImpl;

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
