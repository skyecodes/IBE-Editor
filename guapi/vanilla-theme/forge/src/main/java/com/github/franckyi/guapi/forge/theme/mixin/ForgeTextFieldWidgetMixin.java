package com.github.franckyi.guapi.forge.theme.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.IReorderingProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;

@Mixin(TextFieldWidget.class)
public interface ForgeTextFieldWidgetMixin {
    @Accessor("frame")
    int getFrame();

    @Accessor("bordered")
    boolean isBordered();

    @Accessor("canLoseFocus")
    boolean canLoseFocus();

    @Accessor("isEditable")
    boolean isEditable();

    @Accessor("shiftPressed")
    void setShiftPressed(boolean shiftPressed);

    @Accessor("displayPos")
    int getDisplayPos();

    @Accessor("displayPos")
    void setDisplayPos(int start);

    @Accessor("cursorPos")
    int getCursorPos();

    @Accessor("highlightPos")
    int getHighlightPos();

    @Accessor("textColor")
    int getTextColor();

    @Accessor("textColorUneditable")
    int getTextColorUneditable();

    @Accessor("suggestion")
    String getSuggestion();

    @Accessor("formatter")
    BiFunction<String, Integer, IReorderingProcessor> getFormatter();

    @Invoker("getMaxLength")
    int invokeGetMaxLength();

    @Invoker("renderHighlight")
    void invokeRenderHighlight(int x1, int y1, int x2, int y2);
}
