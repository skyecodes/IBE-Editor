package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.TagMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.TagController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.TagView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.TagControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.TagViewImpl;

public class TagMVCImpl implements TagMVC {
    public static final TagMVC INSTANCE = new TagMVCImpl();

    protected TagMVCImpl() {
    }

    @Override
    public TagView createView() {
        return new TagViewImpl();
    }

    @Override
    public TagController getController() {
        return TagControllerImpl.INSTANCE;
    }
}
