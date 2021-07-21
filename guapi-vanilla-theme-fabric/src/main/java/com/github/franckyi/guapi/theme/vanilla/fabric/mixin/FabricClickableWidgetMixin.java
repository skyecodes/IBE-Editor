package com.github.franckyi.guapi.theme.vanilla.fabric.mixin;

import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClickableWidget.class)
public interface FabricClickableWidgetMixin {
    @Accessor("height")
    void setHeight(int height);
}
