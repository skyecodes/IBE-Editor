package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.util.Objects;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements ForgeVanillaDelegateRenderer {
    private final TextField node;

    public ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().font, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get());
        this.node = node;
        initLabeled(node, this);
        setMaxLength(node.getMaxLength());
        setValue(node.getText());
        moveCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setResponder(node::setText);
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursorPosition();
            setValue(newVal);
            setCursorPosition(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        node.textRendererProperty().addListener(this::updateRenderer);
        node.cursorPositionProperty().addListener(super::setCursorPosition);
        node.highlightPositionProperty().addListener(super::setHighlightPos);
        updateValidator();
        updateRenderer();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setFilter(Objects::nonNull);
            } else {
                setFilter(node.getValidator());
            }
        } else {
            setFilter(Objects::nonNull);
        }
    }

    private void updateRenderer() {
        if (node.getTextRenderer() == null) {
            setFormatter((string, integer) -> IReorderingProcessor.forward(string, Style.EMPTY));
        } else {
            setFormatter((string, integer) -> renderText(string, integer).getVisualOrderText());
        }
        moveCursorToStart(); // fix in order to render text
    }

    @Override
    public void setCursorPosition(int value) {
        super.setCursorPosition(value);
        node.setCursorPosition(getCursorPosition());
    }

    @Override
    public void setHighlightPos(int value) {
        super.setHighlightPos(value);
        node.setHighlightPosition(highlightPos);
    }

    @Override
    protected void renderHighlight(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_) {
        FontRenderer font = Minecraft.getInstance().font;
        int firstCharacterIndex = displayPos;
        int cursorPosition = node.getCursorPosition();
        int highlightPosition = node.getHighlightPosition();
        int start = Math.min(cursorPosition, highlightPosition);
        int end = Math.max(cursorPosition, highlightPosition);
        Integer newDisplayPos = null;
        if (start < firstCharacterIndex) {
            start = firstCharacterIndex;
            if (cursorPosition == start) {
                newDisplayPos = start;
            }
        }
        ITextComponent fullText = renderText(getValue().substring(firstCharacterIndex), firstCharacterIndex);
        String trimmedText = font.substrByWidth(fullText, this.getInnerWidth()).getString();
        if (cursorPosition == end && end > trimmedText.length() + firstCharacterIndex) {
            newDisplayPos = end - trimmedText.length();
        }
        ITextComponent previousText = renderText(getValue().substring(firstCharacterIndex, start), firstCharacterIndex);
        int previousTextWidth = font.width(previousText);
        ITextComponent highlightedText = renderText(getValue().substring(start, end), start);
        int highlightedTextWidth = font.width(highlightedText);
        int x0 = x + 4;
        if (newDisplayPos != null) {
            displayPos = newDisplayPos;
        }
        super.renderHighlight(x0 + previousTextWidth, p_146188_2_, x0 + previousTextWidth + highlightedTextWidth, p_146188_4_);
    }

    public ITextComponent renderText(String str, int firstCharacterIndex) {
        return node.getTextRenderer() == null ? new StringTextComponent(str) : node.getTextRenderer().render(str, firstCharacterIndex).get();
    }

    @Override
    public void insertText(String string) {
        int oldCursorPos = getCursorPosition();
        int oldHighlightPos = node.getHighlightPosition();
        String oldText = getValue();
        super.insertText(string);
        node.onTextUpdate(oldCursorPos, oldHighlightPos, oldText, getCursorPosition(), getValue());
    }

    @Override
    public void deleteChars(int characterOffset) {
        if (getHighlighted().isEmpty()) {
            int oldCursorPos = getCursorPosition();
            int oldHighlightPos = node.getHighlightPosition();
            String oldText = getValue();
            super.deleteChars(characterOffset);
            node.onTextUpdate(oldCursorPos, oldHighlightPos, oldText, getCursorPosition(), getValue());
        } else {
            super.deleteChars(characterOffset);
        }
    }
}
