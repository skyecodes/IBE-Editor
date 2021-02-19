package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.NBTEditorView;

public interface NBTEditorMVC extends MVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
}
