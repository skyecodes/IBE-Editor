package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

public abstract class ValueEntryController<M extends ValueEntryModel<?>, V extends LabeledEntryView> extends LabeledEntryController<M, V> {
    public ValueEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getResetButton().disableProperty().bind(model.valueChangedProperty().not());
    }
}
