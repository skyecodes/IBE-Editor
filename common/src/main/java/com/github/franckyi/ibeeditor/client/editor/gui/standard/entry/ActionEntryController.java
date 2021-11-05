package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

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
