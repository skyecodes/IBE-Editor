package com.github.franckyi.guapi.forge.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.forge.theme.mixin.ForgeTextFieldWidgetMixin;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
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
        setMaxLength(node.getMaxLength());
        setValue(node.getText());
        setFocused(node.isFocused());
        setResponder(node::setText);
        node.xProperty().addListener(newVal -> x = newVal + 1);
        node.yProperty().addListener(newVal -> y = newVal + 1);
        node.widthProperty().addListener(newVal -> setWidth(newVal - 2));
        node.heightProperty().addListener(newVal -> setHeight(newVal - 2));
        node.disabledProperty().addListener(newVal -> active = !newVal);
        node.labelProperty().addListener(newVal -> setMessage(newVal.get()));
        node.maxLengthProperty().addListener(this::setMaxLength);
        node.textProperty().addListener(this::updateText);
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

    private void updateText(String text) {
        if (node.getValidator().test(text)) {
            if (text.length() > node.getMaxLength()) {
                ((ForgeTextFieldWidgetMixin) this).setRawValue(text.substring(0, node.getMaxLength()));
            } else {
                ((ForgeTextFieldWidgetMixin) this).setRawValue(text);
            }
        }
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
        if (getCursorPosition() < ((ForgeTextFieldWidgetMixin) this).getDisplayPos()) {
            ((ForgeTextFieldWidgetMixin) this).setDisplayPos(getCursorPosition());
        }
    }

    @Override
    public void setHighlightPos(int value) {
        super.setHighlightPos(value);
        node.setHighlightPosition(((ForgeTextFieldWidgetMixin) this).getHighlightPos());
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
        ForgeTextFieldWidgetMixin self = (ForgeTextFieldWidgetMixin) this;
        self.setShiftPressed(false);
        int displayPos = self.getDisplayPos();
        FontRenderer font = Minecraft.getInstance().font;
        ITextProperties string = font.substrByWidth(renderText(getValue().substring(displayPos), displayPos), getInnerWidth());
        setHighlightPos(font.substrByWidth(string, MathHelper.floor(mouseX) - x - 4).getString().length() + displayPos);
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        ForgeTextFieldWidgetMixin self = (ForgeTextFieldWidgetMixin) this;
        FontRenderer font = Minecraft.getInstance().font;
        if (!isVisible()) {
            return false;
        } else {
            boolean flag = p_231044_1_ >= x && p_231044_1_ < x + width && p_231044_3_ >= y && p_231044_3_ < y + height;
            if (self.canLoseFocus()) {
                setFocus(flag);
            }

            if (isFocused() && flag && p_231044_5_ == 0) {
                int i = MathHelper.floor(p_231044_1_) - x;
                if (self.isBordered()) {
                    i -= 4;
                }

                ITextProperties string = font.substrByWidth(renderText(getValue().substring(self.getDisplayPos()), self.getDisplayPos()), getInnerWidth());
                moveCursorTo(font.substrByWidth(string, i).getString().length() + self.getDisplayPos());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        ForgeTextFieldWidgetMixin self = (ForgeTextFieldWidgetMixin) this;
        FontRenderer font = Minecraft.getInstance().font;
        if (isVisible()) {
            if (self.isBordered()) {
                int i = isFocused() ? -1 : -6250336;
                fill(matrices, x - 1, y - 1, x + width + 1, y + height + 1, i);
                fill(matrices, x, y, x + width, y + height, -16777216);
            }

            int i2 = self.isEditable() ? self.getTextColor() : self.getTextColorUneditable();
            int j = self.getCursorPos() - self.getDisplayPos();
            int k = self.getHighlightPos() - self.getDisplayPos();
            ITextComponent renderedText = renderText(getValue().substring(self.getDisplayPos()), self.getDisplayPos());
            String s = renderedText != null
                    ? font.substrByWidth(renderedText, getInnerWidth()).getString()
                    : font.plainSubstrByWidth(getValue().substring(self.getDisplayPos()), this.getInnerWidth());
            boolean flag = j >= 0 && j <= s.length();
            boolean flag1 = isFocused() && self.getFrame() / 6 % 2 == 0 && flag;
            int l = self.isBordered() ? x + 4 : x;
            int i1 = self.isBordered() ? y + (height - 8) / 2 : y;
            int j1 = l;
            if (k > s.length()) {
                k = s.length();
            }

            if (!s.isEmpty()) {
                String s1 = flag ? s.substring(0, j) : s;
                j1 = font.drawShadow(matrices, self.getFormatter().apply(s1, self.getDisplayPos()), l, i1, i2);
            }

            boolean flag2 = self.getCursorPos() < getValue().length() || getValue().length() >= self.invokeGetMaxLength();
            int k1 = j1;
            if (!flag) {
                k1 = j > 0 ? l + width : l;
            } else if (flag2) {
                k1 = j1 - 1;
                --j1;
            }

            if (!s.isEmpty() && flag && j < s.length()) {
                font.drawShadow(matrices, self.getFormatter().apply(s.substring(j), self.getCursorPos()), j1, i1, i2);
            }

            if (!flag2 && self.getSuggestion() != null) {
                font.drawShadow(matrices, self.getSuggestion(), (k1 - 1), i1, -8355712);
            }

            if (flag1) {
                if (flag2) {
                    AbstractGui.fill(matrices, k1, i1 - 1, k1 + 1, i1 + 1 + 9, -3092272);
                } else {
                    font.drawShadow(matrices, "_", k1, i1, i2);
                }
            }

            if (k != j) {
                int firstCharacterIndex = self.getDisplayPos();
                int cursorPosition = self.getCursorPos();
                int highlightPosition = self.getHighlightPos();
                int start = Math.max(Math.min(cursorPosition, highlightPosition), firstCharacterIndex);
                int end = Math.max(cursorPosition, highlightPosition);
                ITextComponent fullText = renderText(getValue().substring(firstCharacterIndex), firstCharacterIndex);
                String trimmedText = font.substrByWidth(fullText, getInnerWidth()).getString();
                if (cursorPosition == end && end > trimmedText.length() + firstCharacterIndex) {
                    self.setDisplayPos(end - trimmedText.length());
                }
                ITextComponent previousText = renderText(getValue().substring(firstCharacterIndex, start), firstCharacterIndex);
                int previousTextWidth = font.width(previousText);
                ITextComponent highlightedText = renderText(getValue().substring(start, end), start);
                int highlightedTextWidth = font.width(highlightedText);
                int x0 = x + 4;
                self.invokeRenderHighlight(x0 + previousTextWidth, i1 - 1, x0 + previousTextWidth + highlightedTextWidth, i1 + 1 + 9);
            }

        }
    }
}
