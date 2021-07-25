package com.github.franckyi.guapi.fabric.theme.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TextFieldWidget.class)
public interface FabricTextFieldWidgetAccessorMixin {
    @Accessor("selectionEnd")
    int getSelectionEnd();
}
