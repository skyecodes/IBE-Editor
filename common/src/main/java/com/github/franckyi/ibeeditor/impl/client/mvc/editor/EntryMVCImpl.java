package com.github.franckyi.ibeeditor.impl.client.mvc.editor;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.EntryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry.IntegerEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry.StringEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry.TextEntryController;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.IntegerEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.StringEntryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.TextEntryView;

public class EntryMVCImpl implements EntryMVC {
    public static final EntryMVC INSTANCE = new EntryMVCImpl();

    @Override
    public EntryView createViewAndBind(EntryModel model) {
        EntryController<?, ?> controller;
        switch (model.getType()) {
            case STRING:
                controller = new StringEntryController((StringEntryModel) model, new StringEntryView());
                break;
            case INTEGER:
                controller = new IntegerEntryController((IntegerEntryModel) model, new IntegerEntryView());
                break;
            case TEXT:
                controller = new TextEntryController(((TextEntryModel) model), new TextEntryView());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model.getType());
        }
        controller.bind();
        return controller.getView();
    }
}
