package com.github.franckyi.guapi.fabric.theme.mixin;

import com.github.franckyi.guapi.fabric.theme.vanilla.FabricVanillaTextFieldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public abstract class FabricTextFieldWidgetRedirectMixin {
    @Shadow
    private int firstCharacterIndex;

    @Shadow
    @Final
    private TextRenderer textRenderer;

    @Shadow
    private int selectionEnd;

    @Shadow
    private int selectionStart;

    @Shadow
    protected abstract void drawSelectionHighlight(int x1, int y1, int x2, int y2);

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;trimToWidth(Ljava/lang/String;I)Ljava/lang/String;"))
    private String replaceRenderedText(TextRenderer textRenderer, String text, int maxWidth) {
        if (FabricVanillaTextFieldRenderer.class.isInstance(this)) {
            FabricVanillaTextFieldRenderer textField = FabricVanillaTextFieldRenderer.class.cast(this);
            Text renderedText = textField.renderText(text, firstCharacterIndex);
            if (renderedText != null) {
                return textRenderer.trimToWidth(renderedText, maxWidth).getString();
            }
        }
        return textRenderer.trimToWidth(text, maxWidth);
    }

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;drawSelectionHighlight(IIII)V"))
    private void fixDrawSelectionHighlight(TextFieldWidget textFieldWidget, int x1, int y1, int x2, int y2) {
        if (textFieldWidget instanceof FabricVanillaTextFieldRenderer) {
            FabricVanillaTextFieldRenderer self = (FabricVanillaTextFieldRenderer) textFieldWidget;
            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
            int firstCharacterIndex = this.firstCharacterIndex;
            int cursorPosition = selectionStart;
            int highlightPosition = selectionEnd;
            int start = Math.min(cursorPosition, highlightPosition);
            int end = Math.max(cursorPosition, highlightPosition);
            if (start < firstCharacterIndex) {
                start = firstCharacterIndex;
                if (cursorPosition == start) {
                    this.firstCharacterIndex = start;
                }
            }
            Text fullText = self.renderText(self.getText().substring(firstCharacterIndex), firstCharacterIndex);
            String trimmedText = textRenderer.trimToWidth(fullText, self.getInnerWidth()).getString();
            if (cursorPosition == end && cursorPosition > trimmedText.length() + firstCharacterIndex) {
                this.firstCharacterIndex = cursorPosition - trimmedText.length();
            }
            Text previousText = self.renderText(self.getText().substring(firstCharacterIndex, start), firstCharacterIndex);
            int previousTextWidth = textRenderer.getWidth(previousText);
            Text highlightedText = self.renderText(self.getText().substring(start, end), start);
            int highlightedTextWidth = textRenderer.getWidth(highlightedText);
            int x0 = self.x + 4;
            drawSelectionHighlight(x0 + previousTextWidth, y1, x0 + previousTextWidth + highlightedTextWidth, y2);
        }
    }

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(II)Ljava/lang/String;"))
    private String replaceTextSubstring(String s, int beginIndex, int endIndex) {
        if (FabricVanillaTextFieldRenderer.class.isInstance(this) && endIndex < 0) {
            return null; // or "" ??
        }
        return s.substring(beginIndex, endIndex);
    }

    @Redirect(method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setCursor(I)V"))
    private void replaceSetCursorOnClick(TextFieldWidget self, int cursor, double mouseX, double mouseY, int button) {
        if (self instanceof FabricVanillaTextFieldRenderer) {
            FabricVanillaTextFieldRenderer textField = (FabricVanillaTextFieldRenderer) self;
            StringVisitable string = textRenderer.trimToWidth(textField.renderText(textField.getText().substring(firstCharacterIndex), firstCharacterIndex), textField.getInnerWidth());
            textField.setCursor(textRenderer.trimToWidth(string, MathHelper.floor(mouseX) - textField.x - 4).getString().length() + firstCharacterIndex);
        }
    }
}
