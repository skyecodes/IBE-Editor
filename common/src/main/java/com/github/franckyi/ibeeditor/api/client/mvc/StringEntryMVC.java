package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.StringEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.StringEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;

public interface StringEntryMVC extends MVC<StringEntryModel, StringEntryView, StringEntryController> {
}
