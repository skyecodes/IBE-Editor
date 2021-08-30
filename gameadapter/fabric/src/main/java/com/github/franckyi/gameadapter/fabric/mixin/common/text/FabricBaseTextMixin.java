package com.github.franckyi.gameadapter.fabric.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import com.github.franckyi.gameadapter.fabric.common.IFabricStyle;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(BaseText.class)
@Implements(@Interface(iface = IText.class, prefix = "proxy$"))
public abstract class FabricBaseTextMixin implements MutableText {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> proxy$getExtra() {
        return (List) getSiblings();
    }

    public void proxy$addExtra(IText extra) {
        append((Text) extra);
    }

    public String proxy$getColor() {
        TextColor color = getStyle().getColor();
        return color == null ? null : color.getName();
    }

    public void proxy$setColor(String color) {
        styled(style -> style.withColor(color == null ? null : TextColor.parse(color)));
    }

    public Boolean proxy$getBold() {
        return ((IStyle) getStyle()).getBold();
    }

    public void proxy$setBold(Boolean bold) {
        styled(style -> style.withBold(bold));
    }

    public Boolean proxy$getItalic() {
        return ((IStyle) getStyle()).getItalic();
    }

    public void proxy$setItalic(Boolean italic) {
        styled(style -> style.withItalic(italic));
    }

    public Boolean proxy$getUnderlined() {
        return ((IStyle) getStyle()).getUnderlined();
    }

    public void proxy$setUnderlined(Boolean underlined) {
        styled(style -> style.withUnderline(underlined));
    }

    public Boolean proxy$getStrikethrough() {
        return ((IStyle) getStyle()).getStrikethrough();
    }

    public void proxy$setStrikethrough(Boolean strikethrough) {
        styled(style -> ((IFabricStyle) style).withStrikethrough(strikethrough));
    }

    public Boolean proxy$getObfuscated() {
        return ((IStyle) getStyle()).getObfuscated();
    }

    public void proxy$setObfuscated(Boolean obfuscated) {
        styled(style -> ((IFabricStyle) style).withObfuscated(obfuscated));
    }

    public ITextEvent proxy$getClickEvent() {
        return (ITextEvent) getStyle().getClickEvent();
    }

    public void proxy$setClickEvent(ITextEvent clickEvent) {
        styled(style -> style.withClickEvent((ClickEvent) clickEvent));
    }

    public ITextEvent proxy$getHoverEvent() {
        return (ITextEvent) getStyle().getHoverEvent();
    }

    public void proxy$setHoverEvent(ITextEvent hoverEvent) {
        styled(style -> style.withHoverEvent((HoverEvent) hoverEvent));
    }

    public String proxy$getRawText() {
        return getString();
    }

    public String proxy$toJson() {
        return Text.Serializer.toJson(this);
    }

    public IText proxy$copy() {
        return (IText) copy();
    }
}
