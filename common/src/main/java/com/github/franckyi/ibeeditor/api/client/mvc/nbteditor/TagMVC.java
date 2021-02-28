package com.github.franckyi.ibeeditor.api.client.mvc.nbteditor;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.TagView;

public interface TagMVC extends MVC<TagModel, TagView, TagController> {
}
