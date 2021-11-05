package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

public abstract class LabeledEntryController<M extends LabeledEntryModel, V extends LabeledEntryView> extends EntryController<M, V> {
    public LabeledEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
        if (view.getLabel().getParent() == view.getRoot()) {
            view.getRoot().setWeight(view.getLabel(), model.getLabelWeight());
        }
    }
}
