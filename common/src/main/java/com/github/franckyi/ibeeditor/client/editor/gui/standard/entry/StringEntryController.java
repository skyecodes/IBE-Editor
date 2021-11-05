package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

public class StringEntryController<M extends StringEntryModel, V extends StringEntryView> extends ValueEntryController<M, V> {
    public StringEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().textProperty().addListener(model::setValue);
        model.valueProperty().addListener(view.getTextField()::setText);
        view.getTextField().validProperty().addListener(model::setValid);
        view.getTextField().setText(model.getValue());
    }
}
