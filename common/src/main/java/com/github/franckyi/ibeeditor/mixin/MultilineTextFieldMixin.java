package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.components.MultilineTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MultilineTextField.class)
public interface MultilineTextFieldMixin {
    @Accessor("value")
    void setRawValue(String text);

    @Accessor
    void setWidth(int value);

    @Invoker
    void invokeReflowDisplayLines();
}
