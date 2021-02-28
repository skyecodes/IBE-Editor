package com.github.franckyi.ibeeditor.api.client.mvc.nbteditor;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.NBTEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.NBTEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.NBTEditorView;

public interface NBTEditorMVC extends MVC<NBTEditorModel, NBTEditorView, NBTEditorController> {
}
