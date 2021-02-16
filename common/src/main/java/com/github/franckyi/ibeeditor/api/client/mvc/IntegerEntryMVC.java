package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.IntegerEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;

public interface IntegerEntryMVC extends MVC<IntegerEntryModel, IntegerEntryView, IntegerEntryController> {
}
