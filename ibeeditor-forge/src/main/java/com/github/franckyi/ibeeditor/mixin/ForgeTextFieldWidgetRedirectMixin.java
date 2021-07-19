package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.guapi.impl.theme.vanilla.ForgeVanillaTextFieldRenderer;
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
    protected int displayPos;

    @Shadow
    @Final
    private FontRenderer font;

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

    @Redirect(method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;moveCursorTo(I)V"))
    private void replaceSetCursorOnClick(TextFieldWidget self, int cursor, double mouseX, double mouseY, int button) {
        if (self instanceof ForgeVanillaTextFieldRenderer) {
            ForgeVanillaTextFieldRenderer textField = (ForgeVanillaTextFieldRenderer) self;
            ITextProperties string = font.substrByWidth(textField.renderText(textField.getValue().substring(displayPos), displayPos), textField.getInnerWidth());
            textField.moveCursorTo(font.substrByWidth(string, MathHelper.floor(mouseX) - textField.x - 4).getString().length() + displayPos);
        }
    }
}
