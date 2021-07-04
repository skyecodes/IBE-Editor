package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.AbstractListEditorController;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class StandardEditorControllerImpl extends AbstractListEditorController<StandardEditorModel, StandardEditorView> implements StandardEditorController {
    public StandardEditorControllerImpl(StandardEditorModel model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getHeaderText().with(translated(model.getTitle()));
        model.focusedTextEntryProperty().addListener(value -> view.setShowTextButtons(value != null));
        view.setOnTextButtonClick(this::onTextButtonClick);
        if (model.isDisabled()) {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().setTooltip(model.getDisabledTooltip());
        }
    }

    @Override
    protected void onValidationChange(boolean newVal) {
        if (!model.isDisabled()) {
            super.onValidationChange(newVal);
        }
    }

    private void onTextButtonClick(StandardEditorView.TextButtonType textButtonType) {
        if (textButtonType == StandardEditorView.TextButtonType.CUSTOM_COLOR) {

        } else {
            model.getFocusedTextEntry().addFormatting(textButtonType);
        }
    }
}
