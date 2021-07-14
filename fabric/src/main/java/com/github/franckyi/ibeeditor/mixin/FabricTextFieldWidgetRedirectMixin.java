package com.github.franckyi.ibeeditor.mixin;

import com.github.franckyi.guapi.impl.theme.vanilla.FabricVanillaTextFieldRenderer;
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
