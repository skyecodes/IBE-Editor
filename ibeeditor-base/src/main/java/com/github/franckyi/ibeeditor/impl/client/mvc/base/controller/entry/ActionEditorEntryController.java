package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.ActionEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.ActionEditorEntryView;

public class ActionEditorEntryController extends AbstractEditorEntryController<ActionEditorEntryModel, ActionEditorEntryView> {
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
