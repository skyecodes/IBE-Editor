package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.controller.StringEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.StringEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.StringEntryView;

public class StringEntryControllerImpl implements StringEntryController {
    public static final StringEntryController INSTANCE = new StringEntryControllerImpl();

    private StringEntryControllerImpl() {
    }

    @Override
    public void init(StringEntryModel model, StringEntryView view) {
        view.getLabel().setLabel(model.getLabel());
        view.getTextField().setText(model.getValue());
        view.getResetButton().onAction(() -> view.getTextField().setText(model.getDefaultValue()));
        model.valueProperty().unbind();
        model.valueProperty().bind(view.getTextField().textProperty());
        model.valueProperty().addListener(() -> this.onValueChange(model, view));
        this.onValueChange(model, view);
    }

    private void onValueChange(StringEntryModel model, StringEntryView view) {
        view.getResetButton().setDisable(model.getValue().equals(model.getDefaultValue()));
    }
}
