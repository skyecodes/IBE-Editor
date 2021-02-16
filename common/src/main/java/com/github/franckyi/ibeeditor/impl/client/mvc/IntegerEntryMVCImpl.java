package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.IntegerEntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.IntegerEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.IntegerEntryControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.IntegerEntryViewImpl;

public class IntegerEntryMVCImpl implements IntegerEntryMVC {
    public static final IntegerEntryMVC INSTANCE = new IntegerEntryMVCImpl();

    private IntegerEntryMVCImpl() {
    }

    @Override
    public IntegerEntryView createView() {
        return new IntegerEntryViewImpl();
    }

    @Override
    public IntegerEntryController getController() {
        return IntegerEntryControllerImpl.INSTANCE;
    }
}
