package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractWidget.class)
public interface AbstractWidgetMixin {
    @Accessor("height")
    void setHeight(int height);
}
