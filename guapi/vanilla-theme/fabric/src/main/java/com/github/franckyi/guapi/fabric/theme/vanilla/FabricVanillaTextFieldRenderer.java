package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricClickableWidgetMixin;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricTextFieldWidgetMixin;
import net.minecraft.client.MinecraftClient;
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
        active = !node.isDisabled();
        setMaxLength(node.getMaxLength());
        setText(node.getText());
        setFocused(node.isFocused());
        setChangedListener(node::setText);
        node.xProperty().addListener(newVal -> x = newVal + 1);
        node.yProperty().addListener(newVal -> y = newVal + 1);
        node.widthProperty().addListener(newVal -> setWidth(newVal - 2));
        node.heightProperty().addListener(newVal -> ((FabricClickableWidgetMixin) this).setHeight(newVal - 2));
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.labelProperty().addListener(newVal -> setMessage(newVal.get()));
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
        node.placeholderProperty().addListener(this::updatePlaceholder);
        node.textProperty().addListener(this::updatePlaceholder);
        setCursorToStart(); // fix in order to render text
        updateValidator();
        updateRenderer();
        updatePlaceholder();
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

    private void updatePlaceholder() {
        setSuggestion(getText().isEmpty() ? node.getPlaceholder().getRawText() : null);
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
