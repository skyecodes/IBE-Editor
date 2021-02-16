package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.controller.IntegerEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.IntegerEntryView;

public class IntegerEntryControllerImpl implements IntegerEntryController {
    public static final IntegerEntryController INSTANCE = new IntegerEntryControllerImpl();

    private IntegerEntryControllerImpl() {
    }

    @Override
    public void init(IntegerEntryModel model, IntegerEntryView view) {
        view.getLabel().setLabel(model.getLabel());
    }
}
