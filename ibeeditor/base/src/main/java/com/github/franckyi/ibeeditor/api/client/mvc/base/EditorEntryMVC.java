package com.github.franckyi.ibeeditor.api.client.mvc.base;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.EditorEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

public interface EditorEntryMVC extends MVC<EditorEntryModel, EditorEntryView, EditorEntryController<EditorEntryModel, EditorEntryView>> {
}
