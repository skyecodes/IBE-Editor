package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.SNBTEditorView;
import com.github.franckyi.ibeeditor.common.EditorContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;

public class SNBTEditorController extends AbstractController<SNBTEditorModel, SNBTEditorView> {
    public SNBTEditorController(SNBTEditorModel model, SNBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        /*if (model.canSaveToVault()) {
            view.addSaveVaultButton();
            model.saveToVaultProperty().bind(view.getSaveVaultButton().activeProperty());
        }*/
        view.addOpenEditorButton(() -> model.changeEditor(EditorContext.EditorType.STANDARD));
        view.addOpenNBTEditorButton(() -> model.changeEditor(EditorContext.EditorType.NBT));
        view.getTextArea().textProperty().bindBidirectional(model.valueProperty());
        view.getTextArea().setValidator(s -> {
            try {
                return TagParser.parseTag(s) != null;
            } catch (CommandSyntaxException e) {
                return false;
            }
        });
        /*if (model.canSave()) {*/
        view.getDoneButton().disableProperty().bind(view.getTextArea().validProperty().not());
        view.getDoneButton().onAction(event -> {
            model.apply();
            Guapi.getScreenHandler().hideScene();
        });
        /*} else {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }*/
        view.getCancelButton().onAction(event -> Guapi.getScreenHandler().hideScene());
    }
}
