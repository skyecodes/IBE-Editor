package com.github.franckyi.ibeeditor.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;

@Mixin(EditBox.class)
public interface EditBoxMixin {
    @Accessor("value")
    void setRawValue(String text);

    @Accessor("focusedTime")
    long getFocusedTime();

    @Accessor("bordered")
    boolean isBordered();

    @Accessor("canLoseFocus")
    boolean canLoseFocus();

    @Accessor("isEditable")
    boolean isEditable();

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
    BiFunction<String, Integer, FormattedCharSequence> getFormatter();

    @Invoker("getMaxLength")
    int invokeGetMaxLength();

    @Invoker("renderHighlight")
    void invokeRenderHighlight(GuiGraphics guiGraphics, int x1, int y1, int x2, int y2);
}
