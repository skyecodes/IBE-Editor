package com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor;

import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.TagMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.model.TagModel;
import com.github.franckyi.ibeeditor.api.client.mvc.nbteditor.view.TagView;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.controller.TagControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.nbteditor.view.TagViewImpl;

public class TagMVCImpl implements TagMVC {
    public static final TagMVC INSTANCE = new TagMVCImpl();

    protected TagMVCImpl() {
    }

    @Override
    public TagView createView(Class<? extends TagModel> aClass) {
        if (aClass.isAssignableFrom())
        return new TagViewImpl();
    }

    @Override
    public TagController createController() {
        return TagControllerImpl.INSTANCE;
    }
}
