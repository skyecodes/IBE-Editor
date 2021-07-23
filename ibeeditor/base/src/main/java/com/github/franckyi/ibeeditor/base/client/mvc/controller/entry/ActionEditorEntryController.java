package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ActionEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ActionEditorEntryView;

public class ActionEditorEntryController extends EditorEntryController<ActionEditorEntryModel, ActionEditorEntryView> {
    public ActionEditorEntryController(ActionEditorEntryModel model, ActionEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getButton().setLabel(model.getLabel());
        view.getButton().onAction(model.getAction());
    }
}
