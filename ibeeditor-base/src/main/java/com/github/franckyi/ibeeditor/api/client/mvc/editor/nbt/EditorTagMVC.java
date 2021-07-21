package com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.controller.EditorTagController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.model.EditorTagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.nbt.view.EditorTagView;

public interface EditorTagMVC extends MVC<EditorTagModel, EditorTagView, EditorTagController> {
}
