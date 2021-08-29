package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import com.github.franckyi.gameadapter.forge.common.IForgeStyle;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(TextComponent.class)
@Implements(@Interface(iface = IText.class, prefix = "proxy$"))
public abstract class ForgeTextComponentMixin implements IFormattableTextComponent {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> proxy$getExtra() {
        return (List) getSiblings();
    }

    public void proxy$addExtra(IText extra) {
        append((ITextComponent) extra);
    }

    public String proxy$getColor() {
        net.minecraft.util.text.Color color = getStyle().getColor();
        return color == null ? null : color.serialize();
    }

    public void proxy$setColor(String color) {
        withStyle(style -> style.withColor(color == null ? null : net.minecraft.util.text.Color.parseColor(color)));
    }

    public Boolean proxy$getBold() {
        return ((IStyle) getStyle()).getBold();
    }

    public void proxy$setBold(Boolean bold) {
        withStyle(style -> style.withBold(bold));
    }

    public Boolean proxy$getItalic() {
        return ((IStyle) getStyle()).getItalic();
    }

    public void proxy$setItalic(Boolean italic) {
        withStyle(style -> style.withItalic(italic));
    }

    public Boolean proxy$getUnderlined() {
        return ((IStyle) getStyle()).getUnderlined();
    }

    public void proxy$setUnderlined(Boolean underlined) {
        withStyle(style -> style.withUnderlined(underlined));
    }

    public Boolean proxy$getStrikethrough() {
        return ((IStyle) getStyle()).getStrikethrough();
    }

    public void proxy$setStrikethrough(Boolean strikethrough) {
        withStyle(style -> ((IForgeStyle) style).withStrikethrough(strikethrough));
    }

    public Boolean proxy$getObfuscated() {
        return ((IStyle) getStyle()).getObfuscated();
    }

    public void proxy$setObfuscated(Boolean obfuscated) {
        withStyle(style -> ((IForgeStyle) style).withObfuscated(obfuscated));
    }

    public ITextEvent proxy$getClickEvent() {
        return (ITextEvent) getStyle().getClickEvent();
    }

    public void proxy$setClickEvent(ITextEvent clickEvent) {
        withStyle(style -> style.withClickEvent((ClickEvent) clickEvent));
    }

    public ITextEvent proxy$getHoverEvent() {
        return (ITextEvent) getStyle().getHoverEvent();
    }

    public void proxy$setHoverEvent(ITextEvent hoverEvent) {
        withStyle(style -> style.withHoverEvent((HoverEvent) hoverEvent));
    }

    public String proxy$getRawText() {
        return getString();
    }

    public String proxy$toJson() {
        return ITextComponent.Serializer.toJson(this);
    }

    public IText proxy$copy() {
        return (IText) plainCopy();
    }
}
