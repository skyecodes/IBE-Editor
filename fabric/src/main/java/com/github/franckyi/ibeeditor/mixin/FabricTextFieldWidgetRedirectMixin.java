package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.guapi.impl.theme.vanilla.FabricVanillaTextFieldRenderer;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public abstract class FabricTextFieldWidgetRedirectMixin {
    @Shadow
    private int firstCharacterIndex;

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
}
