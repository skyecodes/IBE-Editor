package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TextFieldWidget.class)
public interface TextFieldWidgetMixin {
    @Accessor("selectionEnd")
    int getSelectionEnd();

    @Accessor("firstCharacterIndex")
    int getFirstCharacterIndex();

    @Accessor("firstCharacterIndex")
    void setFirstCharacterIndex(int value);
}
