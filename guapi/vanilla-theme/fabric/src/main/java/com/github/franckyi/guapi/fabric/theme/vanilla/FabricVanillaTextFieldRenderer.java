package com.github.franckyi.guapi.fabric.theme.vanilla;

import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricClickableWidgetMixin;
import com.github.franckyi.guapi.fabric.theme.mixin.FabricTextFieldWidgetMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.*;
import net.minecraft.util.math.MathHelper;

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
        node.textProperty().addListener(this::updateText);
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

    private void updateText(String text) {
        if (node.getValidator().test(text)) {
            if (text.length() > node.getMaxLength()) {
                ((FabricTextFieldWidgetMixin) this).setRawText(text.substring(0, node.getMaxLength()));
            } else {
                ((FabricTextFieldWidgetMixin) this).setRawText(text);
            }
        }
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

    public Text renderText(String str, int firstCharacterIndex) {
        return node.getTextRenderer() == null ? new LiteralText(str) : node.getTextRenderer().render(str, firstCharacterIndex).get();
    }

    @Override
    public void doTick() {
        tick();
    }

    @Override
    public void setSelectionStart(int value) {
        super.setSelectionStart(value);
        node.setCursorPosition(getCursor());
        if (getCursor() < ((FabricTextFieldWidgetMixin) this).getFirstCharacterIndex()) {
            ((FabricTextFieldWidgetMixin) this).setFirstCharacterIndex(getCursor());
        }
    }

    @Override
    public void setSelectionEnd(int value) {
        super.setSelectionEnd(value);
        node.setHighlightPosition(((FabricTextFieldWidgetMixin) this).getSelectionEnd());
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

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        FabricTextFieldWidgetMixin self = (FabricTextFieldWidgetMixin) this;
        self.setSelecting(false);
        int firstCharacterIndex = self.getFirstCharacterIndex();
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        StringVisitable string = textRenderer.trimToWidth(renderText(getText().substring(firstCharacterIndex), firstCharacterIndex), getInnerWidth());
        setSelectionEnd(textRenderer.trimToWidth(string, MathHelper.floor(mouseX) - x - 4).getString().length() + firstCharacterIndex);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        FabricTextFieldWidgetMixin self = (FabricTextFieldWidgetMixin) this;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        if (!isVisible()) {
            return false;
        } else {
            boolean bl = mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
            if (self.isFocusUnlocked()) {
                setTextFieldFocused(bl);
            }

            if (this.isFocused() && bl && button == 0) {
                int i = MathHelper.floor(mouseX) - x;
                if (self.drawsBackground()) {
                    i -= 4;
                }
                StringVisitable string = textRenderer.trimToWidth(renderText(getText().substring(self.getFirstCharacterIndex()), self.getFirstCharacterIndex()), getInnerWidth());
                setCursor(textRenderer.trimToWidth(string, i).getString().length() + self.getFirstCharacterIndex());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        FabricTextFieldWidgetMixin self = (FabricTextFieldWidgetMixin) this;
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        if (isVisible()) {
            int j;
            if (self.drawsBackground()) {
                j = isFocused() ? -1 : -6250336;
                fill(matrices, x - 1, y - 1, x + width + 1, y + height + 1, j);
                fill(matrices, x, y, x + width, y + height, -16777216);
            }

            j = self.isEditable() ? self.getEditableColor() : self.getUneditableColor();
            int k = self.getSelectionStart() - self.getFirstCharacterIndex();
            int l = self.getSelectionEnd() - self.getFirstCharacterIndex();
            Text renderedText = renderText(getText().substring(self.getFirstCharacterIndex()), self.getFirstCharacterIndex());
            String string = renderedText != null
                    ? textRenderer.trimToWidth(renderedText, getInnerWidth()).getString()
                    : textRenderer.trimToWidth(getText().substring(self.getFirstCharacterIndex()), getInnerWidth());
            boolean bl = k >= 0 && k <= string.length();
            boolean bl2 = isFocused() && self.getFocusedTicks() / 6 % 2 == 0 && bl;
            int m = self.drawsBackground() ? x + 4 : x;
            int n = self.drawsBackground() ? y + (height - 8) / 2 : y;
            int o = m;
            if (l > string.length()) {
                l = string.length();
            }

            if (!string.isEmpty()) {
                String string2 = bl ? string.substring(0, k) : string;
                o = textRenderer.drawWithShadow(matrices, self.getRenderTextProvider().apply(string2, self.getFirstCharacterIndex()), m, n, j);
            }

            boolean bl3 = self.getSelectionStart() < getText().length() || getText().length() >= self.invokeGetMaxLength();
            int p = o;
            if (!bl) {
                p = k > 0 ? m + width : m;
            } else if (bl3) {
                p = o - 1;
                --o;
            }

            if (!string.isEmpty() && bl && k < string.length()) {
                textRenderer.drawWithShadow(matrices, self.getRenderTextProvider().apply(string.substring(k), self.getSelectionStart()), o, n, j);
            }

            if (!bl3 && self.getSuggestion() != null) {
                textRenderer.drawWithShadow(matrices, self.getSuggestion(), p - 1, n, -8355712);
            }

            int y1;
            int x2;
            int y2;
            if (bl2) {
                if (bl3) {
                    y1 = n - 1;
                    x2 = p + 1;
                    y2 = n + 1;
                    DrawableHelper.fill(matrices, p, y1, x2, y2 + 9, -3092272);
                } else {
                    textRenderer.drawWithShadow(matrices, "_", p, n, j);
                }
            }

            if (l != k) {
                y1 = n - 1;
                y2 = n + 1;
                int firstCharacterIndex = self.getFirstCharacterIndex();
                int cursorPosition = self.getSelectionStart();
                int highlightPosition = self.getSelectionEnd();
                int start = Math.max(Math.min(cursorPosition, highlightPosition), firstCharacterIndex);
                int end = Math.max(cursorPosition, highlightPosition);
                Text fullText = renderText(getText().substring(firstCharacterIndex), firstCharacterIndex);
                String trimmedText = textRenderer.trimToWidth(fullText, getInnerWidth()).getString();
                if (cursorPosition == end && cursorPosition > trimmedText.length() + firstCharacterIndex) {
                    self.setFirstCharacterIndex(cursorPosition - trimmedText.length());
                }
                Text previousText = renderText(getText().substring(firstCharacterIndex, start), firstCharacterIndex);
                int previousTextWidth = textRenderer.getWidth(previousText);
                Text highlightedText = renderText(getText().substring(start, end), start);
                int highlightedTextWidth = textRenderer.getWidth(highlightedText);
                int x0 = x + 4;
                self.invokeDrawSelectionHighlight(x0 + previousTextWidth, y1, x0 + previousTextWidth + highlightedTextWidth, y2 + 9);
            }
        }
    }
}
