package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.StandardEditorView;
import com.github.franckyi.ibeeditor.common.EditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class StandardEditorController extends CategoryEntryScreenController<StandardEditorModel, StandardEditorView> {
    public StandardEditorController(StandardEditorModel model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        /*if (model.canSaveToVault()) {
            view.addSaveVaultButton();
            model.saveToVaultProperty().bind(view.getSaveVaultButton().activeProperty());
        }*/
        view.addOpenNBTEditorButton(() -> model.changeEditor(EditorContext.EditorType.NBT));
        view.addOpenSNBTEditorButton(() -> model.changeEditor(EditorContext.EditorType.SNBT));
        if (model.getContext().getTag() == null) {
            view.getOpenNBTEditorButton().setDisable(true);
            view.getOpenSNBTEditorButton().setDisable(true);
        }
        view.getHeaderLabel().setLabel(ModTexts.editorTitle(switch (model.getContext().getTarget()) {
            case ITEM -> ModTexts.ITEM;
            case BLOCK -> ModTexts.BLOCK;
            case ENTITY -> ModTexts.ENTITY;
        }));
        model.activeTextEditorProperty().addListener(value -> view.setShowTextButtons(value != null));
        view.setTextEditorSupplier(model::getActiveTextEditor);
        /*if (model.isDisabled()) {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }*/
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
    protected void onValidationChange(boolean modelValid) {
        //if (!model.isDisabled()) {
        super.onValidationChange(modelValid);
        //}
    }
}
