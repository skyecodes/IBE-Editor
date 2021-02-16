package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.StringEntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.StringEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.StringEntryControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.StringEntryViewImpl;

public class StringEntryMVCImpl implements StringEntryMVC {
    public static final StringEntryMVC INSTANCE = new StringEntryMVCImpl();

    private StringEntryMVCImpl() {
    }

    @Override
    public StringEntryView createView() {
        return new StringEntryViewImpl();
    }

    @Override
    public StringEntryController getController() {
        return StringEntryControllerImpl.INSTANCE;
    }
}
