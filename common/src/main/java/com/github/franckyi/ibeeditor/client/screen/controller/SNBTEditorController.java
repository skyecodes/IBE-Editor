package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.SNBTEditorModel;
import com.github.franckyi.ibeeditor.client.screen.view.SNBTEditorView;
import com.github.franckyi.ibeeditor.common.EditorType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;

public class SNBTEditorController extends AbstractController<SNBTEditorModel, SNBTEditorView> implements EditorController<SNBTEditorModel, SNBTEditorView> {
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
        view.getFormatButton().onAction(this::format);
        view.getDoneButton().onAction(model::update);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
    }

    private void format() {
        String text = model.getValue().replaceAll("\\s", "");
        int depth = 0;
        StringBuilder newText = new StringBuilder();
        for (char c : text.toCharArray()) {
            boolean isNewline = false;
            if (c == '{' || c == '[') {
                depth++;
                isNewline = true;
            } else if (c == ',') {
                isNewline = true;
            } else if (c == '}' || c == ']') {
                depth--;
                newLine(newText, depth);
            }
            newText.append(c);
            if (isNewline) {
                newLine(newText, depth);
            }
        }
        model.setValue(newText.toString().trim());
    }

    private void newLine(StringBuilder newText, int depth) {
        newText.append("\n").append("  ".repeat(depth));
    }
}
