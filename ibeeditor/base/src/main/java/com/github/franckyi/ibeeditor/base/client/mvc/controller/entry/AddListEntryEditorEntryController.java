package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AddListEntryEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.AddListEntryEditorEntryView;

public class AddListEntryEditorEntryController extends EditorEntryController<AddListEntryEditorEntryModel, AddListEntryEditorEntryView> {
    public AddListEntryEditorEntryController(AddListEntryEditorEntryModel model, AddListEntryEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        getView().getButton().getTooltip().add(getModel().getTooltip());
        getView().getButton().onAction(getModel().getCategory()::addEntryInList);
    }
}
