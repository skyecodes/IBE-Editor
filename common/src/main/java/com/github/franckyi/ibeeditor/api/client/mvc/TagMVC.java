package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;

public interface TagMVC extends MVC<TagModel, TagView, TagController> {
}
