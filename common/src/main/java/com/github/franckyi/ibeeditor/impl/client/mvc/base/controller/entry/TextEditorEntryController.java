package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.*;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryController extends LabeledEditorEntryController<TextEditorEntryModel, TextEditorEntryView> implements TextEditorActionHandler {
    private List<Formatting> formattings;

    public TextEditorEntryController(TextEditorEntryModel model, TextEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setText(model.getValue().getRawText());
        view.getTextField().setTextRenderer(this::renderText);
        view.getTextField().focusedProperty().addListener(this::onTextFieldFocus);
        model.validProperty().bind(view.getTextField().validProperty());
        model.setValueFactory(this::createText);
        initFormattings(model.getValue());
    }

    private Text renderText(String str, int firstCharacterIndex) {
        if (!str.isEmpty()) {
            TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(text().extra(text("")));
            formatter.format(str, firstCharacterIndex, formattings);
            return formatter.getText();
        }
        return Text.EMPTY;
    }

    private PlainText createText() {
        TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(text());
        formatter.format(view.getTextField().getText(), 0, formattings);
        return formatter.getText();
    }

    private void initFormattings(PlainText text) {
        TextEditorInputParser parser = new TextEditorInputParser();
        parser.parse(text);
        formattings = parser.getFormattings();
    }

    private void onTextFieldFocus(boolean focused) {
        if (focused) {
            model.getCategory().getEditor().setActiveTextEditor(this);
        } else if (model.getCategory().getEditor().getActiveTextEditor() == this) {
            model.getCategory().getEditor().setActiveTextEditor(null);
        }
    }

    @Override
    public void addColorFormatting(String color) {
        int i = view.getTextField().getCursorPosition();
        int j = view.getTextField().getHighlightPosition();
        if (i == j) return;
        ColorFormatting formatting = new ColorFormatting(Math.min(i, j), Math.max(i, j), color);
        if (formattings.contains(formatting)) {
            return;
        }
        mergeIdenticalFormattings(ColorFormatting.class, other -> other.getColor().equals(color), formatting);
        formattings.addAll(resizeOtherColorFormattings(formatting, color));
    }

    @Override
    public void addStyleFormatting(StyleType type) {
        int i = view.getTextField().getCursorPosition();
        int j = view.getTextField().getHighlightPosition();
        if (i == j) return;
        StyleFormatting formatting = new StyleFormatting(Math.min(i, j), Math.max(i, j), type);
        if (formattings.contains(formatting)) {
            formattings.remove(formatting);
            return;
        }
        mergeIdenticalFormattings(StyleFormatting.class, other -> other.getType().equals(type), formatting);
        formattings.add(formatting);
    }

    private <T extends Formatting> void mergeIdenticalFormattings(Class<T> formattingClass, Predicate<T> identicalPredicate, T formatting) {
        Iterator<Formatting> it = formattings.iterator();
        while (it.hasNext()) {
            Formatting f = it.next();
            if (formattingClass.isInstance(f)) {
                T other = formattingClass.cast(f);
                if (identicalPredicate.test(other)) {
                    boolean remove = false;
                    if (formatting.getStart() >= other.getStart() && formatting.getStart() <= other.getEnd()) {
                        formatting.setStart(other.getStart());
                        remove = true;
                    }
                    if (formatting.getEnd() >= other.getStart() && formatting.getEnd() <= other.getEnd()) {
                        formatting.setEnd(other.getEnd());
                        remove = true;
                    }
                    if (remove) {
                        it.remove();
                    }
                }
            }
        }
    }

    private List<Formatting> resizeOtherColorFormattings(ColorFormatting formatting, String color) {
        List<Formatting> addedFormattings = Lists.newArrayList(formatting);
        Iterator<Formatting> it = formattings.iterator();
        while (it.hasNext()) {
            Formatting f = it.next();
            if (f instanceof ColorFormatting) {
                ColorFormatting other = (ColorFormatting) f;
                if (!other.getColor().equals(color)) {
                    if (formatting.getStart() <= other.getStart() && formatting.getEnd() >= other.getEnd()) {
                        it.remove();
                        continue;
                    }
                    if (formatting.getStart() <= other.getStart() && formatting.getEnd() > other.getStart() && formatting.getEnd() <= other.getEnd()) {
                        other.setStart(formatting.getEnd());
                    }
                    if (formatting.getEnd() >= other.getEnd() && formatting.getStart() < other.getEnd() && formatting.getStart() >= other.getStart()) {
                        other.setEnd(formatting.getStart());
                    }
                    if (formatting.getStart() >= other.getStart() && formatting.getEnd() > other.getStart() && formatting.getEnd() <= other.getEnd()) {
                        addedFormattings.add(new ColorFormatting(formatting.getEnd(), other.getEnd(), other.getColor()));
                        other.setEnd(formatting.getStart());
                    }
                    if (other.getStart() >= other.getEnd()) {
                        it.remove();
                    }
                }
            }
        }
        return addedFormattings;
    }
}
