package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.StandardEditorView;
import com.github.franckyi.ibeeditor.common.EditorType;

public class StandardEditorController extends CategoryEntryScreenController<StandardEditorModel, StandardEditorView> implements EditorController<StandardEditorModel, StandardEditorView> {
    public StandardEditorController(StandardEditorModel model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        EditorController.super.bind();
        view.addOpenNBTEditorButton(() -> model.changeEditor(EditorType.NBT));
        view.addOpenSNBTEditorButton(() -> model.changeEditor(EditorType.SNBT));
        if (model.getContext().getTag() == null) {
            view.getOpenNBTEditorButton().setDisable(true);
            view.getOpenSNBTEditorButton().setDisable(true);
        }
        view.getHeaderLabel().setLabel(model.getEditorName());
        view.getTextEditorButtons().visibleProperty().bind(model.activeTextEditorProperty().notNull());
        view.setTextEditorSupplier(model::getActiveTextEditor);
        view.getChooseCustomColorButton().onAction(e -> {
            e.consume();
            ModScreenHandler.openColorSelectionScreen(ColorSelectionScreenModel.Target.TEXT, Color.fromHex(model.getTextEditorCustomColor()), this::updateCustomColor);
        });
        model.textEditorCustomColor().addListener(value -> {
            view.getCustomColorButton().setBackgroundColor(Color.fromHex(value));
            view.getCustomColorButton().setVisible(value != null);
        });
        view.getCustomColorButton().onAction(e -> {
            e.consume();
            model.getActiveTextEditor().addColorFormatting(model.getTextEditorCustomColor());
        });
    }

    private void updateCustomColor(String hex) {
        model.setTextEditorCustomColor(hex);
        model.getActiveTextEditor().addColorFormatting(hex);
    }

    @Override
    public void updateDoneButton() {
        EditorController.super.updateDoneButton();
    }
}
