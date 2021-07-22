package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.NBTEditorView;

public interface NBTEditorMVC extends SimpleMVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
}
