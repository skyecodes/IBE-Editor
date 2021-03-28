package com.github.franckyi.ibeeditor.impl.client.mvc.editor;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.EntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.IntegerEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.StringEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.IntegerEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.StringEntryView;

public class EntryMVCImpl implements EntryMVC {
    public static final EntryMVC INSTANCE = new EntryMVCImpl();

    @Override
    public EntryView createViewAndBind(EntryModel model) {
        EntryController<?, ?> controller;
        switch (model.getType()) {
            case STRING:
                controller = new StringEntryController((StringEntryModel) model, new StringEntryView());
                controller.bind();
                return controller.getView();
            case INTEGER:
                controller = new IntegerEntryController((IntegerEntryModel) model, new IntegerEntryView());
                controller.bind();
                return controller.getView();
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
    }
}
