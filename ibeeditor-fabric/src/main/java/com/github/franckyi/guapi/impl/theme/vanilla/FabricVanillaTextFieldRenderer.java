package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.ibeeditor.mixin.FabricTextFieldWidgetMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Objects;

public class FabricVanillaTextFieldRenderer extends TextFieldWidget implements FabricVanillaDelegateRenderer {
    private final TextField node;

    public FabricVanillaTextFieldRenderer(TextField node) {
        super(MinecraftClient.getInstance().textRenderer, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get());
        this.node = node;
        initLabeled(node, this);
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setCursorToStart(); // fix in order to render text
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(newVal -> {
            int cursor = getCursor();
            setText(newVal);
            setCursor(cursor);
        });
        node.focusedProperty().addListener(this::setFocused);
        node.validatorProperty().addListener(this::updateValidator);
        node.validationForcedProperty().addListener(this::updateValidator);
        node.textRendererProperty().addListener(this::updateRenderer);
        node.cursorPositionProperty().addListener(super::setSelectionStart);
        node.highlightPositionProperty().addListener(super::setSelectionEnd);
        updateValidator();
        updateRenderer();
    }

    private void updateValidator() {
        if (node.isValidationForced()) {
            if (node.getValidator() == null) {
                setTextPredicate(Objects::nonNull);
            } else {
                setTextPredicate(node.getValidator());
            }
        } else {
            setTextPredicate(Objects::nonNull);
        }
    }

    private void updateRenderer() {
        if (node.getTextRenderer() == null) {
            setRenderTextProvider((string, integer) -> OrderedText.styledForwardsVisitedString(string, Style.EMPTY));
        } else {
            setRenderTextProvider((string, integer) -> renderText(string, integer).asOrderedText());
        }
        setCursorToStart(); // fix in order to render text
    }

    @Override
    public void setSelectionStart(int value) {
        super.setSelectionStart(value);
        node.setCursorPosition(getCursor());
    }

    @Override
    public void setSelectionEnd(int value) {
        super.setSelectionEnd(value);
        node.setHighlightPosition(((FabricTextFieldWidgetMixin) this).getSelectionEnd());
    }

    @Override
    protected void drawSelectionHighlight(int x1, int y1, int x2, int y2) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int firstCharacterIndex = ((FabricTextFieldWidgetMixin) this).getFirstCharacterIndex();
        int cursorPosition = node.getCursorPosition();
        int highlightPosition = node.getHighlightPosition();
        int start = Math.min(cursorPosition, highlightPosition);
        int end = Math.max(cursorPosition, highlightPosition);
        if (start < firstCharacterIndex) {
            start = firstCharacterIndex;
            if (cursorPosition == start) {
                ((FabricTextFieldWidgetMixin) this).setFirstCharacterIndex(start);
            }
        }
        Text fullText = renderText(getText().substring(firstCharacterIndex), firstCharacterIndex);
        String trimmedText = textRenderer.trimToWidth(fullText, this.getInnerWidth()).getString();
        if (cursorPosition == end && cursorPosition > trimmedText.length() + firstCharacterIndex) {
            ((FabricTextFieldWidgetMixin) this).setFirstCharacterIndex(cursorPosition - trimmedText.length());
        }
        Text previousText = renderText(getText().substring(firstCharacterIndex, start), firstCharacterIndex);
        int previousTextWidth = textRenderer.getWidth(previousText);
        Text highlightedText = renderText(getText().substring(start, end), start);
        int highlightedTextWidth = textRenderer.getWidth(highlightedText);
        int x0 = x + 4;
        super.drawSelectionHighlight(x0 + previousTextWidth, y1, x0 + previousTextWidth + highlightedTextWidth, y2);
    }

    public Text renderText(String str, int firstCharacterIndex) {
        return node.getTextRenderer() == null ? new LiteralText(str) : node.getTextRenderer().render(str, firstCharacterIndex).get();
    }

    @Override
    public void write(String string) {
        int oldCursorPos = getCursor();
        int oldHighlightPos = node.getHighlightPosition();
        String oldText = getText();
        super.write(string);
        node.onTextUpdate(oldCursorPos, oldHighlightPos, oldText, getCursor(), getText());
    }

    @Override
    public void eraseCharacters(int characterOffset) {
        if (getSelectedText().isEmpty()) {
            int oldCursorPos = getCursor();
            int oldHighlightPos = node.getHighlightPosition();
            String oldText = getText();
            super.eraseCharacters(characterOffset);
            node.onTextUpdate(oldCursorPos, oldHighlightPos, oldText, getCursor(), getText());
        } else {
            super.eraseCharacters(characterOffset);
        }
    }
}
