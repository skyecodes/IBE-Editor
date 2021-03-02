package com.github.franckyi.ibeeditor.api.client.mvc.editor;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;

public interface EntryMVC extends MVC<EntryModel, EntryView, EntryController<EntryModel, EntryView>> {
}
