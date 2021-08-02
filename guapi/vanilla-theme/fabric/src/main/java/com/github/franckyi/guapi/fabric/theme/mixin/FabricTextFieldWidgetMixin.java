package com.github.franckyi.guapi.fabric.theme.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;

@Mixin(TextFieldWidget.class)
public interface FabricTextFieldWidgetMixin {
    @Accessor("text")
    void setRawText(String text);

    @Accessor("focusedTicks")
    int getFocusedTicks();

    @Accessor("drawsBackground")
    boolean drawsBackground();

    @Accessor("focusUnlocked")
    boolean isFocusUnlocked();

    @Accessor("editable")
    boolean isEditable();

    @Accessor("selecting")
    void setSelecting(boolean selecting);

    @Accessor("firstCharacterIndex")
    int getFirstCharacterIndex();

    @Accessor("firstCharacterIndex")
    void setFirstCharacterIndex(int start);

    @Accessor("selectionStart")
    int getSelectionStart();

    @Accessor("selectionEnd")
    int getSelectionEnd();

    @Accessor("editableColor")
    int getEditableColor();

    @Accessor("uneditableColor")
    int getUneditableColor();

    @Accessor("suggestion")
    String getSuggestion();

    @Accessor("renderTextProvider")
    BiFunction<String, Integer, OrderedText> getRenderTextProvider();

    @Invoker("getMaxLength")
    int invokeGetMaxLength();

    @Invoker("drawSelectionHighlight")
    void invokeDrawSelectionHighlight(int x1, int y1, int x2, int y2);
}
