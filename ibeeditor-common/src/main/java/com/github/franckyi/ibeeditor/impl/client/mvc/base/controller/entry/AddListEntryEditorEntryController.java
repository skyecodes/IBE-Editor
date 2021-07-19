package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.AddListEntryEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.AddListEntryEditorEntryView;

public class AddListEntryEditorEntryController extends AbstractEditorEntryController<AddListEntryEditorEntryModel, AddListEntryEditorEntryView> {
    public AddListEntryEditorEntryController(AddListEntryEditorEntryModel model, AddListEntryEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        getView().getButton().setTooltip(getModel().getTooltip());
        getView().getButton().onAction(getModel().getCategory()::addEntryInList);
    }
}
