package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AddListEntryEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.AddListEntryEntryView;

public class AddListEntryEntryController extends EntryController<AddListEntryEntryModel, AddListEntryEntryView> {
    public AddListEntryEntryController(AddListEntryEntryModel model, AddListEntryEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        getView().getButton().getTooltip().add(getModel().getTooltip());
        getView().getButton().onAction(getModel().getCategory()::addEntryInList);
    }
}
