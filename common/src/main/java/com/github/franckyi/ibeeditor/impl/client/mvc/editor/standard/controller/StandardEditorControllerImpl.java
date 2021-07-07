package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.controller;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.controller.StandardEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.AbstractListEditorController;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.StyleType;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.TextEditorActionHandler;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class StandardEditorControllerImpl extends AbstractListEditorController<StandardEditorModel, StandardEditorView> implements StandardEditorController, TextEditorActionHandler {
    public StandardEditorControllerImpl(StandardEditorModel model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getHeaderText().with(translated(model.getTitle()));
        model.focusedTextEntryProperty().addListener(value -> view.setShowTextButtons(value != null));
        view.setTextEditorActionHandler(this);
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

    @Override
    public void addColorFormatting(String color) {
        model.getFocusedTextEntry().addColorFormatting(color);
    }

    @Override
    public void addStyleFormatting(StyleType type) {
        model.getFocusedTextEntry().addStyleFormatting(type);
    }
}
