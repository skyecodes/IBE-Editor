package com.github.franckyi.ibeeditor.client.editor.gui.snbt;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;

public class SNBTEditorController extends AbstractController<SNBTEditorModel, SNBTEditorView> {
    public SNBTEditorController(SNBTEditorModel model, SNBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getTextArea().setText(model.getValue());
        view.getTextArea().setValidator(s -> {
            try {
                return TagParser.parseTag(s) != null;
            } catch (CommandSyntaxException e) {
                return false;
            }
        });
        softBind(view.getTextArea().textProperty(), model.valueProperty());
        if (model.canSave()) {
            view.getDoneButton().disableProperty().bind(view.getTextArea().validProperty().not());
            view.getDoneButton().onAction(event -> model.apply());
        } else {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().getTooltip().add(model.getDisabledTooltip());
        }
        view.getCancelButton().onAction(event -> Guapi.getScreenHandler().hideScene());
    }
}
