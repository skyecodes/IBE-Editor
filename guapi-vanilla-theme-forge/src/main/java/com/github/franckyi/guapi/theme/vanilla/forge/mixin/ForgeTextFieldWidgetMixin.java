package com.github.franckyi.guapi.theme.vanilla.forge.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TextFieldWidget.class)
public interface ForgeTextFieldWidgetMixin {
    @Accessor("highlightPos")
    int getHighlightPos();
}
