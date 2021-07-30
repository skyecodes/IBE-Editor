package com.github.franckyi.guapi.forge.theme.mixin;

import com.github.franckyi.guapi.forge.theme.vanilla.ForgeVanillaTextFieldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public abstract class ForgeTextFieldWidgetRedirectMixin {
    @Shadow
    private int displayPos;

    @Shadow
    @Final
    private FontRenderer font;

    @Shadow
    private int cursorPos;

    @Shadow
    private int highlightPos;

    @Shadow
    protected abstract void renderHighlight(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_);

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;plainSubstrByWidth(Ljava/lang/String;I)Ljava/lang/String;"))
    private String replaceRenderedText(FontRenderer fontRenderer, String text, int maxWidth) {
        if (ForgeVanillaTextFieldRenderer.class.isInstance(this)) {
            ForgeVanillaTextFieldRenderer textField = ForgeVanillaTextFieldRenderer.class.cast(this);
            ITextComponent renderedText = textField.renderText(text, displayPos);
            if (renderedText != null) {
                return fontRenderer.substrByWidth(renderedText, maxWidth).getString();
            }
        }
        return fontRenderer.plainSubstrByWidth(text, maxWidth);
    }

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(II)Ljava/lang/String;"))
    private String replaceTextSubstring(String s, int beginIndex, int endIndex) {
        if (ForgeVanillaTextFieldRenderer.class.isInstance(this) && endIndex < 0) {
            return null;
        }
        return s.substring(beginIndex, endIndex);
    }

    @Redirect(method = "renderButton",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;renderHighlight(IIII)V"))
    private void fixDrawSelectionHighlight(TextFieldWidget textFieldWidget, int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_) {
        if (textFieldWidget instanceof ForgeVanillaTextFieldRenderer) {
            ForgeVanillaTextFieldRenderer self = (ForgeVanillaTextFieldRenderer) textFieldWidget;
            FontRenderer font = Minecraft.getInstance().font;
            int firstCharacterIndex = displayPos;
            int cursorPosition = cursorPos;
            int highlightPosition = highlightPos;
            int start = Math.min(cursorPosition, highlightPosition);
            int end = Math.max(cursorPosition, highlightPosition);
            Integer newDisplayPos = null;
            if (start < firstCharacterIndex) {
                start = firstCharacterIndex;
                if (cursorPosition == start) {
                    newDisplayPos = start;
                }
            }
            ITextComponent fullText = self.renderText(self.getValue().substring(firstCharacterIndex), firstCharacterIndex);
            String trimmedText = font.substrByWidth(fullText, self.getInnerWidth()).getString();
            if (cursorPosition == end && end > trimmedText.length() + firstCharacterIndex) {
                newDisplayPos = end - trimmedText.length();
            }
            ITextComponent previousText = self.renderText(self.getValue().substring(firstCharacterIndex, start), firstCharacterIndex);
            int previousTextWidth = font.width(previousText);
            ITextComponent highlightedText = self.renderText(self.getValue().substring(start, end), start);
            int highlightedTextWidth = font.width(highlightedText);
            int x0 = self.x + 4;
            if (newDisplayPos != null) {
                displayPos = newDisplayPos;
            }
            renderHighlight(x0 + previousTextWidth, p_146188_2_, x0 + previousTextWidth + highlightedTextWidth, p_146188_4_);
        } else {
            renderHighlight(p_146188_1_, p_146188_2_, p_146188_3_, p_146188_4_);
        }
    }

    @Redirect(method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;moveCursorTo(I)V"))
    private void replaceSetCursorOnClick(TextFieldWidget self, int cursor, double mouseX, double mouseY, int button) {
        if (self instanceof ForgeVanillaTextFieldRenderer) {
            ForgeVanillaTextFieldRenderer textField = (ForgeVanillaTextFieldRenderer) self;
            ITextProperties string = font.substrByWidth(textField.renderText(textField.getValue().substring(displayPos), displayPos), textField.getInnerWidth());
            textField.moveCursorTo(font.substrByWidth(string, MathHelper.floor(mouseX) - textField.x - 4).getString().length() + displayPos);
        } else {
            self.moveCursorTo(cursor);
        }
    }
}
