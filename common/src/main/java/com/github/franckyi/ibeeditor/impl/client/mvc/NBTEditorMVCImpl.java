package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.NBTEditorMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.NBTEditorControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.NBTEditorViewImpl;

public class NBTEditorMVCImpl implements NBTEditorMVC {
    public static final NBTEditorMVC INSTANCE = new NBTEditorMVCImpl();

    protected NBTEditorMVCImpl() {
    }

    @Override
    public NBTEditorView createView() {
        return new NBTEditorViewImpl();
    }

    @Override
    public NBTEditorController getController() {
        return NBTEditorControllerImpl.INSTANCE;
    }
}
