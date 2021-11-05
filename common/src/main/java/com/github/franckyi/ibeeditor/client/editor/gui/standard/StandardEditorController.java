package com.github.franckyi.ibeeditor.client.editor.gui.standard;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.editor.gui.ListEditorController;
import com.github.franckyi.ibeeditor.client.util.selection.gui.color.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class StandardEditorController extends ListEditorController<StandardEditorModel<?, ?>, StandardEditorView> {
    public StandardEditorController(StandardEditorModel<?, ?> model, StandardEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getHeaderLabel().setLabel(ModTexts.editorTitle(model.getTitle()));
        model.activeTextEditorProperty().addListener(value -> view.setShowTextButtons(value != null));
        view.setTextEditorSupplier(model::getActiveTextEditor);
        if (model.isDisabled()) {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }
        view.getChooseCustomColorButton().onAction(e -> {
            e.consume();
            ModScreenHandler.openColorSelectionScreen(ColorSelectionScreenModel.Target.TEXT, Color.fromHex(model.getCurrentCustomColor()), this::updateCustomColor);
        });
        model.currentCustomColorProperty().addListener(value -> {
            view.getCustomColorButton().setBackgroundColor(Color.fromHex(value));
            view.getCustomColorButton().setVisible(value != null);
        });
        view.getCustomColorButton().onAction(e -> {
            e.consume();
            model.getActiveTextEditor().addColorFormatting(model.getCurrentCustomColor());
        });
    }

    private void updateCustomColor(String hex) {
        model.setCurrentCustomColor(hex);
        model.getActiveTextEditor().addColorFormatting(hex);
    }

    @Override
    protected void onValidationChange(boolean newVal) {
        if (!model.isDisabled()) {
            super.onValidationChange(newVal);
        }
    }
}
