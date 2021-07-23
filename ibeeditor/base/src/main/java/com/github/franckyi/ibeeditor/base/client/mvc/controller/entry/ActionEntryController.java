package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ActionEntryView;

public class ActionEntryController extends EntryController<ActionEntryModel, ActionEntryView> {
    public ActionEntryController(ActionEntryModel model, ActionEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getButton().setLabel(model.getLabel());
        view.getButton().onAction(model.getAction());
    }
}
