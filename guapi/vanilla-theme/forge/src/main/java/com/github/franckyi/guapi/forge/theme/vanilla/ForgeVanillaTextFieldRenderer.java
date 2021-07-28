package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.forge.theme.mixin.ForgeTextFieldWidgetAccessorMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;

import java.util.Objects;

public class ForgeVanillaTextFieldRenderer extends TextFieldWidget implements ForgeVanillaDelegateRenderer {
    private final TextField node;

    public ForgeVanillaTextFieldRenderer(TextField node) {
        super(Minecraft.getInstance().font, node.getX(), node.getY(), node.getWidth(), node.getHeight(), node.getLabel().get());
        this.node = node;
        active = !node.isDisabled();
        setValue(node.getText());
        setMaxLength(node.getMaxLength());
        setFocused(node.isFocused());
        setResponder(node::setText);
        node.xProperty().addListener(newVal -> x = newVal + 1);
        node.yProperty().addListener(newVal -> y = newVal + 1);
        node.widthProperty().addListener(newVal -> setWidth(newVal - 2));
        node.heightProperty().addListener(newVal -> setHeight(newVal - 2));
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.labelProperty().addListener(newVal -> setMessage(newVal.get()));
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
        node.placeholderProperty().addListener(this::updatePlaceholder);
        node.textProperty().addListener(this::updatePlaceholder);
        moveCursorToStart(); // fix in order to render text
        updateValidator();
        updateRenderer();
        updatePlaceholder();
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

    private void updatePlaceholder() {
        setSuggestion(getValue().isEmpty() ? node.getPlaceholder().getRawText() : null);
    }

    @Override
    public void setCursorPosition(int value) {
        super.setCursorPosition(value);
        node.setCursorPosition(getCursorPosition());
    }

    @Override
    public void setHighlightPos(int value) {
        super.setHighlightPos(value);
        node.setHighlightPosition(((ForgeTextFieldWidgetAccessorMixin) this).getHighlightPos());
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

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        ForgeTextFieldWidgetAccessorMixin self = (ForgeTextFieldWidgetAccessorMixin) this;
        self.setShiftPressed(false);
        int displayPos = self.getDisplayPos();
        FontRenderer font = Minecraft.getInstance().font;
        ITextProperties string = font.substrByWidth(renderText(getValue().substring(displayPos), displayPos), getInnerWidth());
        moveCursorTo(font.substrByWidth(string, MathHelper.floor(mouseX) - x - 4).getString().length() + displayPos);
    }
}
