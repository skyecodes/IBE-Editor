package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.TextColorSelectionScreenView;

public class TextColorSelectionScreenController extends ColorSelectionScreenController<TextColorSelectionScreenView> {
    public TextColorSelectionScreenController(ColorSelectionScreenModel model, TextColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExampleText().setColor(model.getHexValue());
    }
}
