package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.guapi.impl.theme.vanilla.ForgeVanillaTextFieldRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public abstract class ForgeTextFieldWidgetRedirectMixin {
    @Shadow
    private int displayPos;

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
}
