package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.StringEntryView;

public class StringEntryController extends ValueEntryController<StringEntryModel, StringEntryView> {
    public StringEntryController(StringEntryModel model, StringEntryView view) {
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
