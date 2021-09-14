package com.github.franckyi.gameadapter.forge.mixin.common.text;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.gameadapter.api.common.text.ITextEvent;
import com.github.franckyi.gameadapter.api.internal.IStyle;
import com.github.franckyi.gameadapter.forge.common.IForgeStyle;
import net.minecraft.network.chat.*;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(BaseComponent.class)
@Implements(@Interface(iface = IText.class, prefix = "proxy$"))
public abstract class ForgeTextComponentMixin implements MutableComponent {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<IText> proxy$getExtra() {
        return (List) getSiblings();
    }

    public void proxy$addExtra(IText extra) {
        append((Component) extra);
    }

    public String proxy$getColor() {
        net.minecraft.network.chat.TextColor color = getStyle().getColor();
        return color == null ? null : color.serialize();
    }

    public void proxy$setColor(String color) {
        withStyle(style -> style.withColor(color == null ? null : net.minecraft.network.chat.TextColor.parseColor(color)));
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
        withStyle(style -> style.setUnderlined(underlined));
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
        return Component.Serializer.toJson(this);
    }

    public IText proxy$copy() {
        return (IText) plainCopy();
    }
}
