package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.SNBTEditorView;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.SnbtPrinterTagVisitor;
import net.minecraft.nbt.TagParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class SNBTEditorController extends AbstractController<SNBTEditorModel, SNBTEditorView> implements EditorController<SNBTEditorModel, SNBTEditorView> {

    private static final Logger LOGGER = LogManager.getLogger();

    public SNBTEditorController(SNBTEditorModel model, SNBTEditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        EditorController.super.bind();
        view.addOpenEditorButton(() -> model.changeEditor(EditorType.STANDARD));
        view.addOpenNBTEditorButton(() -> model.changeEditor(EditorType.NBT));
        view.getTextArea().textProperty().bindBidirectional(model.valueProperty());
        view.getTextArea().setValidator(s -> {
            try {
                return TagParser.parseTag(s) != null;
            } catch (CommandSyntaxException e) {
                return false;
            }
        });
        view.getFormatButton().disableProperty().bind(view.getTextArea().validProperty().not());
        view.getFormatButton().onAction(this::format);
        view.getDoneButton().onAction(model::update);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
    }

    private void format() {
        SnbtPrinterTagVisitor formatter = new SnbtPrinterTagVisitor("  ", 0, new ArrayList<>());
        try {
            model.setValue(formatter.visit(TagParser.parseTag(view.getTextArea().getText())));
        } catch (CommandSyntaxException e) {
            LOGGER.error("Could not parse NBT tag", e);
        }
    }
}
