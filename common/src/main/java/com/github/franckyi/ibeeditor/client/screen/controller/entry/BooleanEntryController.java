package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.ibeeditor.client.screen.model.entry.BooleanEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.BooleanEntryView;

public class BooleanEntryController<M extends BooleanEntryModel, V extends BooleanEntryView> extends ValueEntryController<M, V> {
    public BooleanEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getCheckBox().checkedProperty().addListener(model::setValue);
        model.valueProperty().addListener(view.getCheckBox()::setChecked);
        view.getCheckBox().setChecked(model.getValue());
    }
}
