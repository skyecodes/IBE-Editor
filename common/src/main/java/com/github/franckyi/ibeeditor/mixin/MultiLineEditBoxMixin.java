package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.components.MultilineTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MultiLineEditBox.class)
public interface MultiLineEditBoxMixin {
    @Accessor
    @Mutable
    MultilineTextField getTextField();

    @Invoker
    void invokeSeekCursorScreen(double x, double y);
}
