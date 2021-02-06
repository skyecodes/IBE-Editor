package com.github.franckyi.guapi.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Redirect(method = "renderButton", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;textRenderer:Lnet/minecraft/client/font/TextRenderer;"))
    private TextRenderer textRenderer(TextFieldWidget owner) {
        return MinecraftClient.getInstance().textRenderer; // TODO find a way to set textRenderer without mixin
    }
}
